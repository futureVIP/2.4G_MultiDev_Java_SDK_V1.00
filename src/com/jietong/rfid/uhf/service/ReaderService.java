package com.jietong.rfid.uhf.service;

import java.util.List;

import com.jietong.rfid.uhf.entity.Reader;
import com.jietong.rfid.uhf.tool.CallBack;
import com.jietong.rfid.util.ReaderUtil;

public interface ReaderService {

	public Reader connect(String portName, int baudRate);
	/**
	 * �Ͽ�����
	 * 
	 * @param reader
	 * @return
	 */
	public boolean disconnect(Reader reader);

	/**
	 * ����Ѱ��
	 * 
	 * @param reader
	 * @return 
	 */
	public boolean startMultiTag(Reader reader,CallBack callBack);
	/**
	 * ֹͣѰ��
	 * 
	 * @param reader
	 * @return
	 */
	public boolean stop(Reader reader);
	/**
	 * ��ȡ�汾��
	 * 
	 * @param reader
	 * @return
	 */
	public String version(Reader reader);

	/**
	 * ����GPIO��ƽ
	 * 
	 * @param reader
	 * @param status
	 * @return
	 */
	public boolean setGpio(Reader reader, byte status);

	/**
	 * ��ȡGPIO��ƽ
	 * 
	 * @param reader
	 * @return
	 */
	public Byte getGpio(Reader reader);

	/**
	 * ��ȡ����ģʽ
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getWorkMode(Reader reader);

	/**
	 * ���ù���ģʽ
	 * 
	 * @param reader
	 * @param setFrequencyParameters
	 * @return
	 */
	public boolean setWorkMode(Reader reader, byte[] setFrequencyParameters);

	/**
	 * ����
	 * 
	 * @param reader
	 * @param module
	 * @return
	 */
	public boolean reStart(Reader reader, int module);

	/**
	 * ����ͨ�ŷ�ʽ
	 * 
	 * @param reader
	 * @param pattern
	 * @return
	 */
	public boolean setTransferPattern(Reader reader, int pattern);

	/**
	 * ������
	 * 
	 * @param reader
	 * @param state
	 * @return
	 */
	public boolean setBuzzerState(Reader reader, int state);

	/**
	 * �����ز�����
	 * 
	 * @param reader
	 * @return
	 */
	public boolean startCarrier(Reader reader);

	/**
	 * ֹͣ�ز�����
	 * 
	 * @param reader
	 * @return
	 */
	public boolean stopCarrier(Reader reader);

	/**
	 * ����˥��ϵ��
	 * 
	 * @param reader
	 * @param attenuation
	 * @return
	 */
	public boolean setAttenuation(Reader reader, int attenuation);

	/**
	 * ��ȡ˥��ϵ��
	 * 
	 * @param reader
	 * @return
	 */
	public String getAttenuation(Reader reader);

	/**
	 * ��ȡ�豸��
	 * 
	 * @param reader
	 * @return
	 */
	public String getDevID(Reader reader);

	/**
	 * �����豸��
	 * 
	 * @param reader
	 * @param deviceNo
	 * @return
	 */
	public boolean setDevID(Reader reader, byte[] deviceNo);

	/**
	 * ��ȡ�������
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getNet(Reader reader);

	/**
	 * �����������
	 * 
	 * @param reader
	 * @param netParam
	 * @return
	 */
	public boolean setNet(Reader reader, byte[] netParam);

	/**
	 * �ָ���������
	 * 
	 * @param reader
	 * @return
	 */
	public boolean factoryReset(Reader reader);

	/**
	 * ��ȡwifi����
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getWifi(Reader reader);

	/**
	 * ����wifi����
	 * 
	 * @param reader
	 * @param wifiParam
	 * @return
	 */
	public boolean setWifi(Reader reader, byte[] wifiParam);
	
	List<String> findSerialPorts();
}
