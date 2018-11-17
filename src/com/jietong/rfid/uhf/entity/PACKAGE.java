package com.jietong.rfid.uhf.entity;

import gnu.io.SerialPort;

import java.net.Socket;

import com.jietong.rfid.uhf.tool.BCC;

public class PACKAGE {
	/**
	 * 网口
	 */
	public Socket socket = null;
	/**
	 * 串口
	 */
	public SerialPort serialPort = null;
	/**
	 * 天线个数
	 */
	public static final int ANT_NUMBER = 4;
	/**
	 * 天线数组
	 */
	public int antIsEnd[] = new int[ANT_NUMBER];
	/**
	 * 设备是否连接
	 */
	public boolean deviceConnected = false;
	/**
	 * 循卡线程是否结束
	 */
	public boolean threadStart = false;
	/**
	 * 天线号
	 */
	public int antNo = 0;
	/**
	 * 串口号
	 */
	public int commNo = 0;
	/**
	 * 端口号
	 */
	public int port = 115200;
	/**
	 * 存储主机字符串，端口固定为20058
	 */
	public String host = "";
	/**
	 * TrandPackage中包头计算
	 */
	public int head_count = 0;
	/**
	 * 转换过程中数据计数
	 */
	public int data_count = 0;
	/**
	 * 串口连接
	 */
	public boolean bIsComPort = false;
	/*
	 * 从接收缓冲区转换后的实际数据长度
	 */
	public int pkg_len = 0;
	/**
	 * 接收数据数组
	 */
	public byte recv_buf[] = new byte[4 * 1024];
	/**
	 * 判断连续读卡有数据时
	 */
	public boolean isData = false;
	/**
	 * 串口号
	 */
	public boolean bIsCom;
	/**
	 * 判断是否是断开连接
	 */
	public boolean disconnect = false;
	/**
	 * 判断是否是双U设备
	 */
	public boolean deviceSU = false;
	/**
	 * 2个字节的起始码
	 */
	public byte[] startcode = new byte[2];
	/**
	 * 命令码
	 */
	public byte[] cmd = new byte[2];
	/**
	 * 顺序号
	 */
	public byte seq;
	/**
	 * 长度，0为low,1为high
	 */
	public int start;
	
	public int  len;
	/**
	 * 设备码
	 */
	public byte deviceId;
	/**
	 * 内存位置
	 */
	public byte blank;
	/**
	 * 数据
	 */
	public byte data[] = new byte[100];
	/**
	 * 接收的数据交换
	 */
	public byte data_buf[] = new byte[1024];
	/**
	 * 连续读卡数据
	 */
	public static byte continueData[] = new byte[100];
	/**
	 * 校验码
	 */
	public byte bcc;

	/**
	 * 获取的命令
	 * 
	 * @return
	 */
	/**
	 * reader.startcode = CMD.SEND_START_CODE;
		reader.start = start;
		reader.len = length;
		reader.cmd = cmd;
	 */
	public byte[] getSendCMD(Reader reader, int length) {
		byte[] sendData = new byte[7 + length];
		sendData[0] = startcode[0];
		sendData[1] = startcode[1];
		sendData[2] = cmd[0];
		sendData[3] = cmd[1];
		sendData[4] = (byte) start;
		sendData[5] = (byte) len;
		int count = 0;
		int i = 6;
		if (length > 0) {
			for (; i < sendData.length && count < length; i++) {
				sendData[i] = data[count];
				count++;
			}
		}
		sendData[i] = BCC.checkSum(sendData);
		return sendData;
	}
}