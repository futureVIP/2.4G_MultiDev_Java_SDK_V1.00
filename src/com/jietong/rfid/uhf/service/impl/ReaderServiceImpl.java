package com.jietong.rfid.uhf.service.impl;

import java.util.List;

import serialports.service.SerialPortService;
import serialports.service.impl.SerialPortServiceImpl;

import com.jietong.rfid.uhf.dao.ReaderDao;
import com.jietong.rfid.uhf.dao.impl.ReaderDaoImpl;
import com.jietong.rfid.uhf.entity.Reader;
import com.jietong.rfid.uhf.service.ReaderService;
import com.jietong.rfid.uhf.tool.CallBack;

public class ReaderServiceImpl implements ReaderService {

	SerialPortService serialPortService = new SerialPortServiceImpl();
	ReaderDao readerDao = new ReaderDaoImpl();
	
	@Override
	public Reader connect(String portName, int baudRate) {
		return readerDao.serialPortConnect(portName, baudRate);
	}
	
	@Override
	public boolean disconnect(Reader reader) {
		return readerDao.disconnect(reader);
	}

	@Override
	public String version(Reader reader) {
		return readerDao.version(reader);
	}

	@Override
	public boolean setGpio(Reader reader,byte status) {
		return readerDao.setGpio(reader,status);
	}

	@Override
	public Byte getGpio(Reader reader) {
		return readerDao.getGpio(reader);
	}

	@Override
	public byte[] getWorkMode(Reader reader) {
		return readerDao.getWorkMode(reader);
	}

	public boolean setWorkMode(Reader reader, byte[] setFrequencyParameters) {
		return readerDao.setWorkMode(reader, setFrequencyParameters);
	}

	public boolean reStart(Reader reader, int module) {
		return readerDao.reStart(reader, module);
	}

	@Override
	public boolean setTransferPattern(Reader reader, int pattern) {
		return readerDao.setTransferPattern(reader, pattern);
	}

	@Override
	public boolean setBuzzerState(Reader reader, int state) {
		return readerDao.setBuzzerState(reader,state);
	}
	
	@Override
	public boolean startCarrier(Reader reader) {
		return readerDao.startCarrier(reader);
	}

	@Override
	public boolean stopCarrier(Reader reader) {
		return readerDao.stopCarrier(reader);
	}

	@Override
	public boolean setAttenuation(Reader reader, int attenuation) {
		return readerDao.setAttenuation(reader,attenuation);
	}

	@Override
	public String getAttenuation(Reader reader) {
		return readerDao.getAttenuation(reader);
	}

	@Override
	public String getDevID(Reader reader) {
		return readerDao.getDevID(reader);
	}

	@Override
	public boolean setDevID(Reader reader, byte[] deviceNo) {
		return readerDao.setDevID(reader,deviceNo);
	}

	@Override
	public byte[] getNet(Reader reader) {
		return readerDao.getNet(reader);
	}

	@Override
	public boolean factoryReset(Reader reader) {
		return readerDao.factoryReset(reader);
	}
	
	@Override
	public boolean setNet(Reader reader, byte[] netParam) {
		return readerDao.setNet(reader,netParam);
	}

	@Override
	public byte[] getWifi(Reader reader) {
		return readerDao.getWifi(reader);
	}
	
	@Override
	public boolean setWifi(Reader reader, byte[] wifiParam) {
		return readerDao.setWifi(reader,wifiParam);
	}

	@Override
	public boolean startMultiTag(Reader reader,CallBack callBack) {
		return readerDao.startMultiTag(reader,callBack);
	}

	@Override
	public boolean stop(Reader reader) {
		return readerDao.stop(reader);
	}

	@Override
	public List<String> findSerialPorts() {
		return serialPortService.findSerialPorts();
	}
}
