package serialports.service.impl;

import gnu.io.SerialPort;

import java.util.ArrayList;


import serialports.dao.impl.SerialPortDaoImpl;
import serialports.service.SerialPortService;


public class SerialPortServiceImpl implements SerialPortService {
	
	SerialPortDaoImpl dao = new SerialPortDaoImpl();

	@Override
	public ArrayList<String> findSerialPorts() {
		return dao.findSerialPorts();
	}

	@Override
	public SerialPort open(String port, int baudrate) {
		return dao.open(port, baudrate);		
	}

	@Override
	public void close(SerialPort serialPort) {
		dao.close(serialPort);
	}

	@Override
	public boolean send(SerialPort serialPort, byte[] data) {
		return dao.send(serialPort,data);
	}

	@Override
	public byte[] read(SerialPort serialPort) {
		return dao.read(serialPort);
	}
}
