package com.jietong.rfid.uhf.dao.impl;

import com.jietong.rfid.uhf.dao.ReaderDao;
import com.jietong.rfid.uhf.entity.Reader;
import com.jietong.rfid.uhf.tool.CallBack;

public class ReaderDaoImpl implements ReaderDao {

	@Override
	public Reader serialPortConnect(String portName, int baudRate) {
		Reader reader = new Reader();
		reader.setHost(reader,portName,baudRate);
		if(!reader.connect(reader)){
			reader.deviceConnected = false;
			reader = null;
		}else {
			reader.deviceConnected = true;
		}
		return reader;
	}

	@Override
	public void deviceUnInit(Reader reader) {
		reader.deviceUnInit(reader);
	}

	@Override
	public boolean disconnect(Reader reader) {
		if (!reader.deviceConnected) {
			return false;
		} else {
			reader.deviceUnInit(reader);
			reader.disconnect(reader);
		}
		return true;
	}

	@Override
	public String version(Reader reader) {
		StringBuffer buffer = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		boolean version = reader.version(reader, buffer, buffer2);
		if (version) {
			return "FW" + buffer.toString() + " HW" + buffer2.toString();
		}
		return null;
	}
	
	@Override
	public boolean startMultiTag(Reader reader,CallBack callBack) {
		return reader.startMultiTag(reader,callBack);
	}

	@Override
	public boolean stop(Reader reader) {
		return reader.stop(reader);
	}

	@Override
	public boolean setGpio(Reader reader,byte state) {
		return reader.setGpio(reader,state);
	}

	@Override
	public Byte getGpio(Reader reader) {
		return reader.getGpio(reader);
	}

	@Override
	public byte[] getWorkMode(Reader reader) {
		return reader.getWorkMode(reader);
	}

	@Override
	public boolean setWorkMode(Reader reader, byte[] setFrequencyParameters) {
		return reader.setWorkMode(reader,setFrequencyParameters);
	}
	
	@Override
	public boolean reStart(Reader reader, int module) {
		return reader.reStart(reader,module);
	}

	@Override
	public boolean setTransferPattern(Reader reader, int pattern) {
		return reader.setTransferPattern(reader, pattern);
	}

	@Override
	public boolean setBuzzerState(Reader reader, int state) {
		return reader.setBuzzerState(reader,state);
	}

	@Override
	public boolean startCarrier(Reader reader) {
		return reader.startCarrier(reader);
	}

	@Override
	public boolean stopCarrier(Reader reader) {
		return reader.stopCarrier(reader);
	}

	@Override
	public boolean setAttenuation(Reader reader, int attenuation) {
		return reader.setAttenuation(reader,attenuation);
	}

	@Override
	public String getAttenuation(Reader reader) {
		return reader.getAttenuation(reader);
	}

	@Override
	public String getDevID(Reader reader) {
		return reader.getDevID(reader);
	}

	@Override
	public boolean setDevID(Reader reader, byte[] deviceNo) {
		return reader.setDevID(reader,deviceNo);
	}

	@Override
	public byte[] getNet(Reader reader) {
		return reader.getNet(reader);
	}

	@Override
	public boolean factoryReset(Reader reader) {
		return reader.factoryReset(reader);
	}

	@Override
	public boolean setNet(Reader reader, byte[] netParam) {
		return reader.setNet(reader,netParam);
	}

	@Override
	public byte[] getWifi(Reader reader) {
		return reader.getWifi(reader);
	}
	
	@Override
	public boolean setWifi(Reader reader, byte[] wifiParam) {
		return reader.setWifi(reader,wifiParam);
	}
}
