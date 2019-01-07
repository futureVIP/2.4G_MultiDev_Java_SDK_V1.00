package com.jietong.rfid.uhf.service;

import com.jietong.rfid.uhf.dao.impl.Reader;
import com.jietong.rfid.uhf.tool.CallBack;

public interface ReaderService {

	/**
	 * 1.Connect Device
	 * @param portName
	 * @param baudRate
	 * @return Object
	 */
	public Reader connect(String portName, int baudRate);
	/**
	 * 2.�Ͽ�����
	 * 
	 * @param reader
	 * @return
	 */
	public boolean disconnect(Reader reader);

	/**
	 * 3.����Ѱ��
	 * 
	 * @param reader
	 * @return 
	 */
	public boolean startMultiTag(Reader reader,CallBack callBack);
	/**
	 * 4.ֹͣѰ��
	 * 
	 * @param reader
	 * @return
	 */
	public boolean stop(Reader reader);
	/**
	 * 5.��ȡ�汾��
	 * 
	 * @param reader
	 * @return
	 */
	public String version(Reader reader);

	/**
	 * 6.����GPIO��ƽ
	 * 
	 * @param reader
	 * @param status
	 * @return
	 */
	public boolean setGpio(Reader reader, byte status);

	/**
	 * 7.��ȡGPIO��ƽ
	 * 
	 * @param reader
	 * @return
	 */
	public Byte getGpio(Reader reader);

	/**
	 * 8.��ȡ����ģʽ
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getWorkMode(Reader reader);

	/**
	 * 9.���ù���ģʽ
	 * 
	 * @param reader
	 * @param setFrequencyParameters
	 * @return
	 */
	public boolean setWorkMode(Reader reader, byte[] setFrequencyParameters);

	/**
	 * 10.����
	 * 
	 * @param reader
	 * @param module
	 * @return
	 */
	public boolean reStart(Reader reader, int module);

	/**
	 * 11.����ͨ�ŷ�ʽ
	 * 
	 * @param reader
	 * @param pattern
	 * @return
	 */
	public boolean setTransferPattern(Reader reader, int pattern);

	/**
	 * 12.������
	 * 
	 * @param reader
	 * @param state
	 * @return
	 */
	public boolean setBuzzerState(Reader reader, int state);

	/**
	 * 13.�����ز�����
	 * 
	 * @param reader
	 * @return
	 */
	public boolean startCarrier(Reader reader);

	/**
	 * 14.ֹͣ�ز�����
	 * 
	 * @param reader
	 * @return
	 */
	public boolean stopCarrier(Reader reader);

	/**
	 * 15.����˥��ϵ��
	 * 
	 * @param reader
	 * @param attenuation
	 * @return
	 */
	public boolean setAttenuation(Reader reader, int attenuation);

	/**
	 * 16.��ȡ˥��ϵ��
	 * 
	 * @param reader
	 * @return
	 */
	public String getAttenuation(Reader reader);

	/**
	 * 17.��ȡ�豸��
	 * 
	 * @param reader
	 * @return
	 */
	public String getDevID(Reader reader);

	/**
	 * 18.�����豸��
	 * 
	 * @param reader
	 * @param deviceNo
	 * @return
	 */
	public boolean setDevID(Reader reader, byte[] deviceNo);

	/**
	 * 19.��ȡ�������
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getNet(Reader reader);

	/**
	 * 20.�����������
	 * 
	 * @param reader
	 * @param netParam
	 * @return
	 */
	public boolean setNet(Reader reader, byte[] netParam);

	/**
	 * 21.�ָ���������
	 * 
	 * @param reader
	 * @return
	 */
	public boolean factoryReset(Reader reader);

	/**
	 * 22.��ȡwifi����
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getWifi(Reader reader);

	/**
	 * 23.����wifi����
	 * 
	 * @param reader
	 * @param wifiParam
	 * @return
	 */
	public boolean setWifi(Reader reader, byte[] wifiParam);
}
