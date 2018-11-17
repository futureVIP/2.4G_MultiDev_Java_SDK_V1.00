package com.jietong.rfid.uhf.tool;

public class CMDReceive {
	public static final int HEAD_LENGTH = 6;
	/**
	 * ��ѯ�汾��
	 */
	public static final byte[] ACTIVE_GET_VERSION = { (byte) 0x81, 0x03 };
	/**
	 * ����GPIO��ƽ
	 */
	public static final byte[] ACTIVE_SET_GPIO = { (byte) 0x82, 0x03 };
	/**
	 * ��ȡGPIO��ƽ
	 */
	public static final byte[] ACTIVE_GET_GPIO = { (byte) 0x83, 0x03 };
	/**
	 * ���ò�����Ϣ
	 */
	public static final byte[] ACTIVE_SET_PARAMETERS = { (byte) 0xC5, 0x03 };
	/**
	 * ��ȡ������Ϣ
	 */
	public static final byte[] ACTIVE_GET_PARAMETERS = { (byte) 0xCD, 0x03 };

	/**
	 * ��д����ģ�鸴λ
	 */
	public static final byte[] ACTIVE_RESET = { (byte) 0x85, 0x03 };

	/**
	 * ����ͨ�ŷ�ʽ
	 */
	public static final byte[] ACTIVE_SET_TRANSFER_MODE = { (byte) 0x9C, 0x03 };
	/**
	 * ������
	 */
	public static final byte[] ACTIVE_BUZZER = { (byte) 0x99, 0x03 };
	/**
	 * �����ز�����
	 */
	public static final byte[] ACTIVE_START_CARRIER = { (byte) 0xC6, 0x03 };
	/**
	 * ֹͣ�ز�����
	 */
	public static final byte[] ACTIVE_STOP_CARRIER = { (byte) 0xC7, 0x03 };
	/**
	 * ��ȡ˥��ϵ��
	 */
	public static final byte[] ACTIVE_GET_ATTENUATION = { (byte) 0xCA, 0x03 };
	/**
	 * ����˥��ϵ��
	 */
	public static final byte[] ACTIVE_SET_ATTENUATION = { (byte) 0xC5, 0x03 };
	/**
	 * �����豸��
	 */
	public static final byte[] ACTIVE_SET_DEVID = { (byte) 0x9A, 0x03 };
	/**
	 * ��ȡ�豸��
	 */
	public static final byte[] ACTIVE_GET_DEVID = { (byte) 0x9B, 0x03 };
	/**
	 * ��ȡ�������
	 */
	public static final byte[] ACTIVE_GET_NET_CONFIG = {(byte) 0x84,0x03};
	/**
	 * �����������
	 */
	public static final byte[] ACTIVE_SET_NET_CONFIG = {(byte) 0x86,0x03};
	/**
	 * �ָ���������
	 */
	public static final byte[] ACTIVE_FACTORY_RESET = {(byte) 0x8E,0x03};
	/**
	 * ����wifi����
	 */
	public static final byte[] ACTIVE_SET_WIFI_CONFIG = {(byte) 0x90,0x03};
	/**
	 * ��ȡwifi����
	 */
	public static final byte[] ACTIVE_GET_WIFI_CONFIG = {(byte) 0x92,0x03};
	/**
	 * ����Ѱ��
	 */
	public static final byte[] ACTIVE_MULITI_ID = {(byte) 0xC1,0x03};
}
