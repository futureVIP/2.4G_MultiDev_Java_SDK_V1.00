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
	 * 2.断开连接
	 * 
	 * @param reader
	 * @return
	 */
	public boolean disconnect(Reader reader);

	/**
	 * 3.连续寻卡
	 * 
	 * @param reader
	 * @return 
	 */
	public boolean startMultiTag(Reader reader,CallBack callBack);
	/**
	 * 4.停止寻卡
	 * 
	 * @param reader
	 * @return
	 */
	public boolean stop(Reader reader);
	/**
	 * 5.获取版本号
	 * 
	 * @param reader
	 * @return
	 */
	public String version(Reader reader);

	/**
	 * 6.设置GPIO电平
	 * 
	 * @param reader
	 * @param status
	 * @return
	 */
	public boolean setGpio(Reader reader, byte status);

	/**
	 * 7.获取GPIO电平
	 * 
	 * @param reader
	 * @return
	 */
	public Byte getGpio(Reader reader);

	/**
	 * 8.获取工作模式
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getWorkMode(Reader reader);

	/**
	 * 9.设置工作模式
	 * 
	 * @param reader
	 * @param setFrequencyParameters
	 * @return
	 */
	public boolean setWorkMode(Reader reader, byte[] setFrequencyParameters);

	/**
	 * 10.重启
	 * 
	 * @param reader
	 * @param module
	 * @return
	 */
	public boolean reStart(Reader reader, int module);

	/**
	 * 11.数据通信方式
	 * 
	 * @param reader
	 * @param pattern
	 * @return
	 */
	public boolean setTransferPattern(Reader reader, int pattern);

	/**
	 * 12.蜂鸣器
	 * 
	 * @param reader
	 * @param state
	 * @return
	 */
	public boolean setBuzzerState(Reader reader, int state);

	/**
	 * 13.开启载波测试
	 * 
	 * @param reader
	 * @return
	 */
	public boolean startCarrier(Reader reader);

	/**
	 * 14.停止载波测试
	 * 
	 * @param reader
	 * @return
	 */
	public boolean stopCarrier(Reader reader);

	/**
	 * 15.设置衰减系数
	 * 
	 * @param reader
	 * @param attenuation
	 * @return
	 */
	public boolean setAttenuation(Reader reader, int attenuation);

	/**
	 * 16.获取衰减系数
	 * 
	 * @param reader
	 * @return
	 */
	public String getAttenuation(Reader reader);

	/**
	 * 17.获取设备号
	 * 
	 * @param reader
	 * @return
	 */
	public String getDevID(Reader reader);

	/**
	 * 18.设置设备号
	 * 
	 * @param reader
	 * @param deviceNo
	 * @return
	 */
	public boolean setDevID(Reader reader, byte[] deviceNo);

	/**
	 * 19.获取网络参数
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getNet(Reader reader);

	/**
	 * 20.设置网络参数
	 * 
	 * @param reader
	 * @param netParam
	 * @return
	 */
	public boolean setNet(Reader reader, byte[] netParam);

	/**
	 * 21.恢复出厂设置
	 * 
	 * @param reader
	 * @return
	 */
	public boolean factoryReset(Reader reader);

	/**
	 * 22.获取wifi配置
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getWifi(Reader reader);

	/**
	 * 23.设置wifi配置
	 * 
	 * @param reader
	 * @param wifiParam
	 * @return
	 */
	public boolean setWifi(Reader reader, byte[] wifiParam);
}
