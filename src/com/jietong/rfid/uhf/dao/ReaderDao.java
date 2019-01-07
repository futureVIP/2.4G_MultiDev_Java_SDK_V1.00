package com.jietong.rfid.uhf.dao;

import com.jietong.rfid.uhf.dao.impl.Reader;
import com.jietong.rfid.uhf.tool.CallBack;

public interface ReaderDao {
	
	public Reader connect(String portName, int baudRate);

	public void deviceUnInit(Reader reader);

	public boolean disconnect(Reader reader);

	public String version(Reader reader);

	public boolean startMultiTag(Reader reader, CallBack callBack);

	public boolean stop(Reader reader);

	public boolean setGpio(Reader reader, byte state);

	public Byte getGpio(Reader reader);

	public byte[] getWorkMode(Reader reader);

	public boolean setWorkMode(Reader reader, byte[] setFrequencyParameters);

	public boolean reStart(Reader reader, int module);

	public boolean setTransferPattern(Reader reader, int pattern);

	public boolean setBuzzerState(Reader reader, int state);

	public boolean startCarrier(Reader reader);

	public boolean stopCarrier(Reader reader);

	public boolean setAttenuation(Reader reader, int attenuation);

	public String getAttenuation(Reader reader);

	public String getDevID(Reader reader);

	public boolean setDevID(Reader reader, byte[] deviceNo);

	public byte[] getNet(Reader reader);

	public boolean factoryReset(Reader reader);

	public boolean setNet(Reader reader, byte[] netParam);

	public byte[] getWifi(Reader reader);

	public boolean setWifi(Reader reader, byte[] wifiParam);
}
