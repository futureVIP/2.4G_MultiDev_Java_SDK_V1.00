package com.jietong.rfid.uhf.dao.impl;

import gnu.io.SerialPort;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

import networks.service.NetworkService;
import networks.service.impl.NetworkServiceImpl;
import serialports.service.SerialPortService;
import serialports.service.impl.SerialPortServiceImpl;

import com.jietong.rfid.uhf.entity.PACKAGE;
import com.jietong.rfid.uhf.tool.CMDReceive;
import com.jietong.rfid.uhf.tool.CMDSend;
import com.jietong.rfid.uhf.tool.CallBack;
import com.jietong.rfid.util.DataConvert;

public class Reader extends PACKAGE {
	
	SerialPortService serviceCom = new SerialPortServiceImpl();
	NetworkService serviceNet = new NetworkServiceImpl();

	public void setHost(Reader reader, String ipOrSerialPort, int baudRate) {
		String comm = ipOrSerialPort.substring(0, 1);
		bisComPort = (comm.equals("C") || comm.equals("c"));
		if(bisComPort){
			port = 115200;
		}else{
			port = baudRate;
		}
		host = ipOrSerialPort;
	}

	public void deviceUnInit(Reader reader) {
		if (!bIsCom) {
			threadStart = false;
		}
		bIsCom = false;
		commNo = 0;
		head_count = 0;
		data_count = 0;
	}

	public boolean connect(Reader reader) {
		boolean flag = false;
		if (bisComPort) {
			try {
				serialPort = serviceCom.open(host, port);
				if (null != serialPort) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			socket = serviceNet.open(host, port);
			if (socket != null) {
				flag = true;
			} else {
				flag = false;
			}
		}
		head_count = 0;
		data_count = 0;
		return flag;
	}

	public void disconnect(Reader reader) {
		if (deviceConnected) {
			if (bisComPort) {
				serviceCom.close(serialPort);
				serialPort = null;
			} else {
				serviceNet.close(socket);
				socket = null;
			}
			deviceConnected = false;
		}
	}

	public boolean version(Reader reader, StringBuffer mainVersion,StringBuffer subVersion) {
		if (null == reader) {
			return false;
		}
		ByteBuffer buffer = ByteBuffer.allocate(10);
		if (sendData(reader, CMDSend.ACTIVE_GET_VERSION, null, 0, 0)) {
			if (readData(reader, CMDReceive.ACTIVE_GET_VERSION, buffer,0, 6)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 6) {
					byte[] version = buffer.array();
					for (int i = 0; i < version.length; i++) {
						if (i < 3) {
							if (i == 2) {
								mainVersion.append(version[i]);
							} else {
								mainVersion.append(version[i] + ".");
							}
						} else if (i < 6) {
							if (i == 5) {
								subVersion.append(version[i]);
							} else {
								subVersion.append(version[i] + ".");
							}
						}
					}
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean sendData(Reader reader, byte[] command, byte[] sendBuf,int _start, int length) {
		startcode = CMDSend.ACTIVE_START_CODE;
		cmd[0] = command[0];
		cmd[1] = command[1];
		start = _start;
		len = length;
		if (length > 0) {
			data = Arrays.copyOf(sendBuf, length);
		} else {
			data = Arrays.copyOf(data, 1);
		}
		byte[] receiveData = getSendCMD(reader, length);
		boolean size = false;
		if (bisComPort) {
			size = serviceCom.send(serialPort, receiveData);
		} else {
			size = serviceNet.send(socket, receiveData);
		}
		if (size) {
			return size;
		} else {
			return size;
		}
	}

	public boolean trandPackage(Reader reader, byte _data) {
		if (head_count < CMDReceive.HEAD_LENGTH) {
			switch (head_count) {
			case 0:
				if (_data == CMDSend.ACTIVE_START_CODE[0]) {
					startcode[0] = _data;
					head_count++;
				}
				break;
			case 1:
				if (_data == CMDSend.ACTIVE_START_CODE[1]) {
					startcode[1] = _data;
					head_count++;
				}
				break;
			case 2:
				cmd[0] = _data;
				head_count++;
				break;
			case 3:
				cmd[1] = _data;
				head_count++;
				break;
			case 4:
				start = _data;
				head_count++;
				break;
			case 5:
				len = _data;
				data = new byte[len];
				data = Arrays.copyOf(data, len);
				head_count++;
				break;
			}
		} else if (data_count < len) {
			data[data_count++] = _data;
			if (data_count == len) {
				bcc = _data;
				head_count = 0;
				data_count = 0;
				return true;
			}
		} else {
			head_count = 0;
			data_count = 0;
			return false;
		}
		return false;
	}

	public boolean readData(Reader reader, byte[] cmd, ByteBuffer buffer,int start, int length) {
		boolean flag = false;
		long begin = System.currentTimeMillis();
		long timeout = 1000;
		boolean once = false;
		byte[] retVal = null;
		while (deviceConnected) {
			long end = System.currentTimeMillis();
			if (end - begin > timeout) {
				// return flag;
			}
			if (once) {
				return flag;
			}
			once = true;
			if (bisComPort) {
				retVal = comReceiveData(reader, serialPort);
			} else {
				// retVal = reader.socketRecv(reader, reader.socket);
			}
			if (null != retVal) {
				for (int i = 0; i < retVal.length; ++i) {
					if (trandPackage(reader, retVal[i])) {
						if (DataConvert.arrayCompareEquals(cmd, this.cmd)) {
							if (buffer != null) {
								buffer.put(Arrays.copyOf(data,len));// 去掉附加的数据长度
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public byte[] socketRecv(Reader reader, Socket socket) {
		if (null == reader) {
			return null;
		}
		byte[] result = serviceNet.read(socket);
		if (null != result) {
			System.out.println("原始数据: " + DataConvert.bytesToHexString(result));
		}
		return result;
	}

	public byte[] comReceiveData(Reader reader, SerialPort serialPort) {
		if (null == reader) {
			return null;
		}
		byte[] result = serviceCom.read(serialPort);
		if (null != result) {
			System.out.println("原始数据: " + DataConvert.bytesToHexString(result));
		}
		return result;
	}

	public Byte getGpio(Reader reader) {
		ByteBuffer buffer = ByteBuffer.allocate(1);
		if (sendData(reader, CMDSend.ACTIVE_GET_GPIO, null, 0, 0)) {
			if (readData(reader, CMDReceive.ACTIVE_GET_GPIO, buffer,start, 1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					return buffer.get(0);
				}
			}
		}
		return null;
	}

	public boolean setGpio(Reader reader, byte state) {
		byte buf[] = new byte[1];
		buf[0] = state;
		ByteBuffer buffer = ByteBuffer.allocate(1);
		if (sendData(reader, CMDSend.ACTIVE_SET_GPIO, buf, 0, 1)) {
			if (readData(reader, CMDReceive.ACTIVE_SET_GPIO, buffer, 0,1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.get(0) == 0x01) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public byte[] getWorkMode(Reader reader) {
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_GET_PARAMETERS, null, 0, 0)) {
			if (readData(reader, CMDReceive.ACTIVE_GET_PARAMETERS,buffer, 0, 0)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 11) {
					return buffer.array();
				}
			}
		}
		return null;
	}

	public boolean setWorkMode(Reader reader, byte[] setFrequencyParameters) {
		byte[] sendbuf = setFrequencyParameters;
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_SET_PARAMETERS, sendbuf, 0,sendbuf.length)) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (readData(reader, CMDReceive.ACTIVE_SET_PARAMETERS,buffer, 0, 1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] == 0x01) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean reStart(Reader reader, int module) {
		byte[] sendbuf = new byte[1];
		sendbuf[0] = (byte) module;
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_RESET, sendbuf, 0,sendbuf.length)) {
			if (readData(reader, CMDReceive.ACTIVE_RESET, buffer, 0, 1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] == 0x01) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean setTransferPattern(Reader reader, int pattern) {
		byte[] sendbuf = new byte[1];
		sendbuf[0] = (byte) pattern;
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_SET_TRANSFER_MODE, sendbuf,	0, sendbuf.length)) {
			if (readData(reader, CMDReceive.ACTIVE_SET_TRANSFER_MODE,buffer, 0, 1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] == 0x01) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean setBuzzerState(Reader reader, int state) {
		byte[] sendbuf = new byte[1];
		sendbuf[0] = (byte) state;
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_BUZZER, sendbuf, 0,sendbuf.length)) {
			if (readData(reader, CMDReceive.ACTIVE_BUZZER, buffer, 0, 1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] == 0x01) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean startCarrier(Reader reader) {
		byte[] sendbuf = new byte[1];
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_START_CARRIER, sendbuf, 0,sendbuf.length)) {
			if (readData(reader, CMDReceive.ACTIVE_START_CARRIER,buffer, 0, 1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] == 0x01) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean stopCarrier(Reader reader) {
		byte[] sendbuf = new byte[1];
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_STOP_CARRIER, sendbuf, 0,sendbuf.length)) {
			if (readData(reader, CMDReceive.ACTIVE_STOP_CARRIER, buffer,0, 1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] == 0x01) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean setAttenuation(Reader reader, int attenuation) {
		byte[] sendbuf = new byte[1];
		sendbuf[0] = (byte) attenuation;
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_SET_ATTENUATION, sendbuf, 0,sendbuf.length)) {
			if (readData(reader, CMDReceive.ACTIVE_SET_ATTENUATION,buffer, 0, 1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] == 0x01) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String getAttenuation(Reader reader) {
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_GET_ATTENUATION, null, 0, 0)) {
			if (readData(reader, CMDReceive.ACTIVE_GET_ATTENUATION,	buffer, 0, 1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					return String.valueOf(DataConvert.byteToInt(buffer.array()[0]));
				}
			}
		}
		return null;
	}

	public String getDevID(Reader reader) {
		StringBuffer deviceNo = new StringBuffer();
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_GET_DEVID, null, 0, 0)) {
			if (readData(reader, CMDReceive.ACTIVE_GET_DEVID, buffer, 0,	1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 4) {
					for (int i = 0; i < 4; i++) {
						deviceNo.append(DataConvert.bytesToHexString(buffer.array()[i]));
					}
					return deviceNo.toString();
				}
			}
		}
		return null;
	}

	public boolean setDevID(Reader reader, byte[] deviceNo) {
		byte[] sendbuf = deviceNo;
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_SET_DEVID, sendbuf, 0,sendbuf.length)) {
			if (readData(reader, CMDReceive.ACTIVE_SET_DEVID, buffer, 0,1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] != 0x00) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean factoryReset(Reader reader) {
		ByteBuffer buffer = ByteBuffer.allocate(20);
		if (sendData(reader, CMDSend.ACTIVE_FACTORY_RESET, null, 0, 0)) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (readData(reader, CMDReceive.ACTIVE_FACTORY_RESET,buffer, 0, 1)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] != 0x00) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public byte[] getNet(Reader reader) {
		ByteBuffer buffer = ByteBuffer.allocate(50);
		if (sendData(reader, CMDSend.ACTIVE_GET_NET_CONFIG, null, 0, 0)) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (readData(reader, CMDReceive.ACTIVE_GET_NET_CONFIG,buffer, 0, 0)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 36) {
					return buffer.array();
				}
			}
		}
		return null;
	}

	public boolean setNet(Reader reader, byte[] netParam) {
		byte[] sendbuf = netParam;
		ByteBuffer buffer = ByteBuffer.allocate(50);
		if (sendData(reader, CMDSend.ACTIVE_SET_NET_CONFIG, sendbuf, 0,	sendbuf.length)) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (readData(reader, CMDReceive.ACTIVE_SET_NET_CONFIG,buffer, 0, 0)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] != 0x00) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public byte[] getWifi(Reader reader) {
		ByteBuffer buffer = ByteBuffer.allocate(80);
		if (sendData(reader, CMDSend.ACTIVE_GET_WIFI_CONFIG, null, 0, 0)) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (readData(reader, CMDReceive.ACTIVE_GET_WIFI_CONFIG,buffer, 0, 0)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 70) {
					return buffer.array();
				}
			}
		}
		return null;
	}

	public boolean setWifi(Reader reader, byte[] wifiParam) {
		byte[] sendbuf = wifiParam;
		ByteBuffer buffer = ByteBuffer.allocate(50);
		if (sendData(reader, CMDSend.ACTIVE_SET_WIFI_CONFIG, sendbuf, 0,
				sendbuf.length)) {
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (readData(reader, CMDReceive.ACTIVE_SET_WIFI_CONFIG,
					buffer, 0, 0)) {
				if (DataConvert.arrayCompareEquals(startcode,CMDSend.ACTIVE_START_CODE) && len == 1) {
					if (buffer.array()[0] != 0x00) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean startMultiTag(Reader reader, CallBack callBack) {
		if (null == reader) {
			return false;
		}
		threadStart = false;
		head_count = 0;
		data_count = 0;
		byte[] sendbuf = { 0x01 };
		if (sendData(reader, CMDSend.ACTIVE_MULITI_ID, sendbuf, 0, 1)) {
			threadStart = true;
			ReaderCard readThread = new ReaderCard(reader, callBack);
			Thread loopThread = new Thread(readThread);
			loopThread.start();
			return true;
		}
		return false;
	}

	protected void threadFunc(final Reader reader, final CallBack callBack) {
		if (null == reader) {
			return;
		}
		boolean exit = true;
		do {
			final byte[] buffer = deviceReadBuffer(reader);
			if (null != buffer) {
				   deviceTransBuffer(reader, buffer, callBack);
			}
			if (!threadStart) {
				if (null == buffer) {
					exit = threadStart;
				}
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (exit);
		// 清理线程资源
		pkg_len = 0;
		head_count = 0;
		data_count = 0;
	}

	private boolean trandPackageContinue(Reader reader, byte filterData,ByteBuffer buffer, ByteBuffer returnLength) {
		if (null == reader) {
			return false;
		}
		if (head_count < CMDReceive.HEAD_LENGTH) {
			switch (head_count) {
			case 0:
				if (filterData == CMDSend.ACTIVE_START_CODE[0]) {
					startcode[0] = filterData;
					head_count++;
				}
				break;
			case 1:
				if (filterData == CMDSend.ACTIVE_START_CODE[1]) {
					startcode[1] = filterData;
					head_count++;
				}
				break;
			case 2:
				cmd[0] = filterData;
				head_count++;
				break;
			case 3:
				cmd[1] = filterData;
				head_count++;
				break;
			case 4:
				start = filterData;
				head_count++;
				break;
			case 5:
				len = DataConvert.byteToInt(filterData);
				data = new byte[len];
				data = Arrays.copyOf(data, len);
				buffer.clear();
				returnLength.clear();
				returnLength.put(filterData);
				head_count++;
				break;
			}
		} else if (data_count < len) {
			buffer.put(filterData);
			data_count++;
		} else {
			if (data_count == len) {
				head_count = 0;
				data_count = 0;
				return true;
			} else {
				head_count = 0;
				data_count = 0;
				return false;
			}
		}
		return false;
	}

	private void deviceTransBuffer(Reader reader, byte[] buffer,CallBack callBack) {
		if (null == reader) {
			return;
		}
		ByteBuffer returnBuffer = ByteBuffer.allocate(4);
		ByteBuffer returnLength = ByteBuffer.allocate(1);
		for (int i = 0; i < buffer.length; i++) {
			if (trandPackageContinue(reader, buffer[i], returnBuffer,returnLength)) {
				int length = DataConvert.byteToInt(returnLength.array()[0]);
				if (length == 4) {
					byte[] readData = Arrays.copyOf(returnBuffer.array(),length);					
					String deviceId = null;
					if (null != socket) {
						deviceId = socket.getInetAddress().getHostName();
					} else {
						deviceId = host;
					}
					callBack.getReadData(DataConvert.bytesToHexString(readData), 0, deviceId);
				}
			}
		}
	}

	private byte[] deviceReadBuffer(Reader reader) {
		if (null == reader) {
			return null;
		}
		byte[] buffer = null;
		if (null != reader) {
			if (bisComPort) {
				buffer = comReceiveData(reader, serialPort);
			} else {
				buffer = socketRecv(reader, socket);// 设置1秒超时
			}
		}
		return buffer;
	}

	public boolean stop(Reader reader) {
		threadStart = false; // 设置线程结束标志
		if (sendData(reader, CMDSend.ACTIVE_STOP_READ_TAG, null, 0, 0)) {
			return true;
		}
		return false;
	}

	class ReaderCard implements Runnable {
		Reader reader = null;
		CallBack callBack = null;

		public ReaderCard() {

		}

		public ReaderCard(Reader reader, CallBack callBack) {
			this.reader = reader;
			this.callBack = callBack;
		}

		public void run() {
			threadFunc(reader, callBack);
		}
	}
}
