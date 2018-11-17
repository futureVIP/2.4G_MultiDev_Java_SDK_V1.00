package com.jietong.rfid.uhf.tool;

/**
 * ������������
 * 
 * @author zhuQixiang
 * 
 */
public class CMDSend {
	/**
	 * ����������ʼ��
	 */
	public static final byte[] ACTIVE_START_CODE = { 0x43, 0x4D };
	/**
	 * ��ȡ�豸�汾��
	 */
	public static final byte[] ACTIVE_GET_VERSION = { 0x01, 0x02 };

	/**
	 * ����GPIO��ƽ
	 */
	public static final byte[] ACTIVE_SET_GPIO = { 0x02, 0x02 };
	/**
	 * ��ȡGPIO��ƽ
	 */
	public static final byte[] ACTIVE_GET_GPIO = { 0x03, 0x02 };
	/**
	 * ���ò�����Ϣ
	 */
	public static final byte[] ACTIVE_SET_PARAMETERS = { 0x45, (byte) 0x02 };
	/**
	 * ��ȡ������Ϣ
	 */
	public static final byte[] ACTIVE_GET_PARAMETERS = { 0x4D, 0x02}; 
	/**
	 * ��д����ģ�鸴λ
	 */
	public static final byte[] ACTIVE_RESET = { (byte) 0x05, 0x02 };
	/**
	 * ����ͨ�ŷ�ʽ
	 */
	public static final byte[]  ACTIVE_SET_TRANSFER_MODE = {0x1C,0x02}; 
	/**
	 * ������
	 */
	public static final byte[] ACTIVE_BUZZER = {(byte) 0x19,0x02};
	/**
	 * �����ز�����
	 */
	public static final byte[] ACTIVE_START_CARRIER = {(byte) 0x46,0x02};
	 /**
	  * ֹͣ�ز�����
	  */
	public static final byte[] ACTIVE_STOP_CARRIER = {(byte) 0x47,0x02};
	/**
	 * ��ȡ˥��ϵ��
	 */
	public static final byte[] ACTIVE_GET_ATTENUATION = {0x4A,0x02};
	/**
	 * ����˥��ϵ��
	 */
	public static final byte[] ACTIVE_SET_ATTENUATION = {0x49,0x02};
	/**
	 * �����豸��
	 */
	public static final byte[] ACTIVE_SET_DEVID = { (byte) 0x1A, 0x02 };
	/**
	 * ��ȡ�豸��
	 */
	public static final byte[] ACTIVE_GET_DEVID = { (byte) 0x1B, 0x02 };
	/**
	 * ��ȡ�������
	 */
	public static final byte[] ACTIVE_GET_NET_CONFIG = {(byte) 0x04,0x02};
	/**
	 * �����������
	 */
	public static final byte[] ACTIVE_SET_NET_CONFIG = {(byte) 0x06,0x02};
	/**
	 * �ָ���������
	 */
	public static final byte[] ACTIVE_FACTORY_RESET = {(byte) 0x0E,0x02};
	/**
	 * ����wifi����
	 */
	public static final byte[] ACTIVE_SET_WIFI_CONFIG = {(byte) 0x10,0x02};
	/**
	 * ��ȡwifi����
	 */
	public static final byte[] ACTIVE_GET_WIFI_CONFIG = {(byte) 0x12,0x02};
	/**
	 * ����Ѱ��
	 */
	public static final byte[] ACTIVE_MULITI_ID = {0x41,0x02};
	/**
	 * ֹͣѰ��
	 */
	public static final byte[] ACTIVE_STOP_READ_TAG = {0x18,0x02};
}
