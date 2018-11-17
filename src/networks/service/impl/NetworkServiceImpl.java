package networks.service.impl;

import java.net.Socket;

import networks.dao.impl.NetworkDaoImpl;
import networks.service.NetworkService;


public class NetworkServiceImpl implements NetworkService{
	
	NetworkDaoImpl dao = new NetworkDaoImpl();

	public Socket open(String host, int port) {
		return dao.open(host, port);
	}
	
	public void close(Socket socket) {
		dao.close(socket);
	}

	public boolean send(Socket socket, byte[] receiveData) {
		return dao.send(socket, receiveData);
	}

	public byte[] read(Socket socket) {
		return dao.read(socket);
	}
}
