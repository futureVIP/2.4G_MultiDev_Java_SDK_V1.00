package serialports.service;

import java.util.ArrayList;

import gnu.io.SerialPort;

public interface SerialPortService {
	
	public ArrayList<String> findSerialPorts();

	public SerialPort open(String port, int baudrate);
	
	public void close(SerialPort serialPort);
	
	public boolean send(SerialPort serialPort, byte[] data);
	
	public byte[] read(SerialPort serialPort);
}
