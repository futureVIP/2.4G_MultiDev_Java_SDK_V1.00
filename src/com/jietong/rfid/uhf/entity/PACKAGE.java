package com.jietong.rfid.uhf.entity;

import gnu.io.SerialPort;

import java.net.Socket;

import com.jietong.rfid.uhf.tool.BCC;

public class PACKAGE {
	/**
	 * ����
	 */
	public Socket socket = null;
	/**
	 * ����
	 */
	public SerialPort serialPort = null;
	/**
	 * ���߸���
	 */
	public static final int ANT_NUMBER = 4;
	/**
	 * ��������
	 */
	public int antIsEnd[] = new int[ANT_NUMBER];
	/**
	 * �豸�Ƿ�����
	 */
	public boolean deviceConnected = false;
	/**
	 * ѭ���߳��Ƿ����
	 */
	public boolean threadStart = false;
	/**
	 * ���ߺ�
	 */
	public int antNo = 0;
	/**
	 * ���ں�
	 */
	public int commNo = 0;
	/**
	 * �˿ں�
	 */
	public int port = 115200;
	/**
	 * �洢�����ַ������˿ڹ̶�Ϊ20058
	 */
	public String host = "";
	/**
	 * TrandPackage�а�ͷ����
	 */
	public int head_count = 0;
	/**
	 * ת�����������ݼ���
	 */
	public int data_count = 0;
	/**
	 * ��������
	 */
	public boolean bIsComPort = false;
	/*
	 * �ӽ��ջ�����ת�����ʵ�����ݳ���
	 */
	public int pkg_len = 0;
	/**
	 * ������������
	 */
	public byte recv_buf[] = new byte[4 * 1024];
	/**
	 * �ж���������������ʱ
	 */
	public boolean isData = false;
	/**
	 * ���ں�
	 */
	public boolean bIsCom;
	/**
	 * �ж��Ƿ��ǶϿ�����
	 */
	public boolean disconnect = false;
	/**
	 * �ж��Ƿ���˫U�豸
	 */
	public boolean deviceSU = false;
	/**
	 * 2���ֽڵ���ʼ��
	 */
	public byte[] startcode = new byte[2];
	/**
	 * ������
	 */
	public byte[] cmd = new byte[2];
	/**
	 * ˳���
	 */
	public byte seq;
	/**
	 * ���ȣ�0Ϊlow,1Ϊhigh
	 */
	public int start;
	
	public int  len;
	/**
	 * �豸��
	 */
	public byte deviceId;
	/**
	 * �ڴ�λ��
	 */
	public byte blank;
	/**
	 * ����
	 */
	public byte data[] = new byte[100];
	/**
	 * ���յ����ݽ���
	 */
	public byte data_buf[] = new byte[1024];
	/**
	 * ������������
	 */
	public static byte continueData[] = new byte[100];
	/**
	 * У����
	 */
	public byte bcc;

	/**
	 * ��ȡ������
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