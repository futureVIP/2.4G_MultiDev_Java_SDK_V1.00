package serialports.dao;

import gnu.io.SerialPort;

import java.util.ArrayList;

public interface SerialPortDao {

	public ArrayList<String> findSerialPorts();

	public SerialPort open(String port, int baudrate);

	public boolean send(SerialPort serialPort, byte[] data);

	public byte[] read(SerialPort serialPort);

	public void close(SerialPort serialPort);
}
