package serialports.dao.impl;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import serialports.dao.SerialPortDao;


@SuppressWarnings("unchecked")
public class SerialPortDaoImpl implements SerialPortDao {

	@Override
	public ArrayList<String> findSerialPorts() {
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		ArrayList<String> portNameList = new ArrayList<String>();
		while (portList.hasMoreElements()) {
			CommPortIdentifier portId = portList.nextElement();
			// String portName = portList.nextElement().getName();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				portNameList.add(portId.getName());
				// portNameList.add(portName);
			}
		}
		return portNameList;
	}

	@Override
	public SerialPort open(String port, int baudrate) {
		// String port = "COM" + portName;
		try {
			CommPortIdentifier portIdentifier = CommPortIdentifier
					.getPortIdentifier(port);
			CommPort commPort = portIdentifier.open(port, 2000);
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				try {
					serialPort.setSerialPortParams(baudrate,SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
				} catch (UnsupportedCommOperationException e) {
					e.printStackTrace();
				}
				System.out.println("Open " + port + " sucessfully !");
				return serialPort;
			} else {
				// throw new NotASerialPort();
			}
		} catch (NoSuchPortException e1) {
			// throw new NoSuchPort();
			return null;
		} catch (PortInUseException e2) {
			System.out.println("exception " + e2.getMessage());
			// throw new PortInUse();
			return null;
		}
		return null;
	}

	@Override
	public void close(SerialPort serialPort) {
		if (serialPort != null) {
			serialPort.close();
			System.out.println("Close " + serialPort + " sucessfully !");
		}
	}

	@Override
	public boolean send(SerialPort serialPort, byte[] data) {
		boolean flag = false;
		OutputStream out = null;
		try {
			out = serialPort.getOutputStream();
			out.write(data);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			out.flush();
			flag = true;
		} catch (IOException e) {

		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				out = null;
			}
		}
		return flag;
	}

	@Override
	public byte[] read(SerialPort serialPort) {
		InputStream in = null;
		byte[] bytes = null;
		try {
			in = serialPort.getInputStream();
			int bufflenth = in.available();
			while (bufflenth != 0) {
				bytes = new byte[bufflenth];
				in.read(bytes);
				bufflenth = in.available();
			}
		} catch (IOException e) {
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				return null;
			}
		}
		return bytes;
	}
}
