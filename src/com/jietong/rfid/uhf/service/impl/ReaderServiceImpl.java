package com.jietong.rfid.uhf.service.impl;

import serialports.service.SerialPortService;
import serialports.service.impl.SerialPortServiceImpl;
import com.jietong.rfid.uhf.dao.ReaderDao;
import com.jietong.rfid.uhf.dao.impl.Reader;
import com.jietong.rfid.uhf.dao.impl.ReaderDaoImpl;
import com.jietong.rfid.uhf.service.ReaderService;
import com.jietong.rfid.uhf.tool.CallBack;

public class ReaderServiceImpl implements ReaderService {

	SerialPortService serialPortService = new SerialPortServiceImpl();
	ReaderDao readerDao = new ReaderDaoImpl();
	
	@Override
	public Reader connect(String portName, int baudRate) {
		return readerDao.connect(portName, baudRate);
	}
	
	@Override
	public boolean disconnect(Reader reader) {
		if (null == reader) {
			return false;
		}
		return readerDao.disconnect(reader);
	}
	
	@Override
	public boolean startMultiTag(Reader reader,CallBack callBack) {
		if (null == reader) {
			return false;
		}
		return readerDao.startMultiTag(reader,callBack);
	}
	
	@Override
	public boolean stop(Reader reader) {
		if (null == reader) {
			return false;
		}
		return readerDao.stop(reader);
	}

	@Override
	public String version(Reader reader) {
		if (null == reader) {
			return null;
		}
		return readerDao.version(reader);
	}

	@Override
	public boolean setGpio(Reader reader,byte status) {
		if (null == reader) {
			return false;
		}
		return readerDao.setGpio(reader,status);
	}

	@Override
	public Byte getGpio(Reader reader) {
		if (null == reader) {
			return null;
		}
		return readerDao.getGpio(reader);
	}

	@Override
	public byte[] getWorkMode(Reader reader) {
		if (null == reader) {
			return null;
		}
		return readerDao.getWorkMode(reader);
	}

	@Override
	public boolean setWorkMode(Reader reader, byte[] setFrequencyParameters) {
		if (null == reader) {
			return false;
		}
		return readerDao.setWorkMode(reader, setFrequencyParameters);
	}

	@Override
	public boolean reStart(Reader reader, int module) {
		if (null == reader) {
			return false;
		}
		return readerDao.reStart(reader, module);
	}

	@Override
	public boolean setTransferPattern(Reader reader, int pattern) {
		if (null == reader) {
			return false;
		}
		return readerDao.setTransferPattern(reader, pattern);
	}

	@Override
	public boolean setBuzzerState(Reader reader, int state) {
		if (null == reader) {
			return false;
		}
		return readerDao.setBuzzerState(reader,state);
	}
	
	@Override
	public boolean startCarrier(Reader reader) {
		if (null == reader) {
			return false;
		}
		return readerDao.startCarrier(reader);
	}

	@Override
	public boolean stopCarrier(Reader reader) {
		if (null == reader) {
			return false;
		}
		return readerDao.stopCarrier(reader);
	}

	@Override
	public boolean setAttenuation(Reader reader, int attenuation) {
		if (null == reader) {
			return false;
		}
		return readerDao.setAttenuation(reader,attenuation);
	}

	@Override
	public String getAttenuation(Reader reader) {
		if (null == reader) {
			return null;
		}
		return readerDao.getAttenuation(reader);
	}

	@Override
	public String getDevID(Reader reader) {
		if (null == reader) {
			return null;
		}
		return readerDao.getDevID(reader);
	}

	@Override
	public boolean setDevID(Reader reader, byte[] deviceNo) {
		if (null == reader) {
			return false;
		}
		return readerDao.setDevID(reader,deviceNo);
	}

	@Override
	public byte[] getNet(Reader reader) {
		if (null == reader) {
			return null;
		}
		return readerDao.getNet(reader);
	}
	
	@Override
	public boolean setNet(Reader reader, byte[] netParam) {
		if (null == reader) {
			return false;
		}
		return readerDao.setNet(reader,netParam);
	}

	@Override
	public boolean factoryReset(Reader reader) {
		if (null == reader) {
			return false;
		}
		return readerDao.factoryReset(reader);
	}
	

	@Override
	public byte[] getWifi(Reader reader) {
		if (null == reader) {
			return null;
		}
		return readerDao.getWifi(reader);
	}
	
	@Override
	public boolean setWifi(Reader reader, byte[] wifiParam) {
		if (null == reader) {
			return false;
		}
		return readerDao.setWifi(reader,wifiParam);
	}
}
