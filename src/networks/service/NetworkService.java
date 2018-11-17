package networks.service;

import java.net.Socket;

public interface NetworkService {
	public Socket open(String port, int baudrate);

	public void close(Socket socket);

	public boolean send(Socket socket, byte[] data);

	public byte[] read(Socket socket);
}
