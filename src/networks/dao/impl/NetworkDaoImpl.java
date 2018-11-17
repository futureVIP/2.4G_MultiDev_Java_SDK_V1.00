package networks.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import networks.dao.NetworkDao;


public class NetworkDaoImpl implements NetworkDao {

	@Override
	public Socket open(String ip, int port) {
		Socket socket = new Socket();
		try {
			InetAddress addr = InetAddress.getByName(ip);
			try {
				socket.connect(new InetSocketAddress(addr, port), 300);
				// socket.setSendBufferSize(100);
				System.out.println("Open " + ip + " sucessfully !");
				return socket;
			} catch (IOException e) {
				System.out.println("Connect failure!");
				// e.printStackTrace();
			}
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public void close(Socket socket) {
		if (socket != null) {
			try {
				socket.close();
				System.out.println("Close " + socket + " sucessfully !");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
			socket = null;
		}
	}

	@Override
	public boolean send(Socket socket, byte[] data) {
		boolean flag = false;
		//System.out.println("send " + socket);
		OutputStream out = null;
		try {
			out = socket.getOutputStream();
			out.write(data);
			Thread.sleep(100);
			out.flush();
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public byte[] read(Socket socket) {
		InputStream in = null;
		byte[] bytes = null;
		try {
			in = socket.getInputStream();
			int bufflenth = in.available(); 
			while (bufflenth != 0) {
				bytes = new byte[bufflenth];
				in.read(bytes);
				bufflenth = in.available();
			}
		} catch (IOException e) {
//			try {
//				in.close();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
			return null;
		} finally {
//			try {
//				if (in != null) {
//					in.close();
//					in = null;
//				}
//			} catch (IOException e) {
//				return null;
//			}
		}
		return bytes;
	}
}
