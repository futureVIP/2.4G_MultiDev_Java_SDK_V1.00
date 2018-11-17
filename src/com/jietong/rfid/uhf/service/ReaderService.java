package com.jietong.rfid.uhf.service;

import java.util.List;

import com.jietong.rfid.uhf.entity.Reader;
import com.jietong.rfid.uhf.tool.CallBack;
import com.jietong.rfid.util.ReaderUtil;

public interface ReaderService {

	public Reader connect(String portName, int baudRate);
	/**
	 * 断开连接
	 * 
	 * @param reader
	 * @return
	 */
	public boolean disconnect(Reader reader);

	/**
	 * 连续寻卡
	 * 
	 * @param reader
	 * @return 
	 */
	public boolean startMultiTag(Reader reader,CallBack callBack);
	/**
	 * 停止寻卡
	 * 
	 * @param reader
	 * @return
	 */
	public boolean stop(Reader reader);
	/**
	 * 获取版本号
	 * 
	 * @param reader
	 * @return
	 */
	public String version(Reader reader);

	/**
	 * 设置GPIO电平
	 * 
	 * @param reader
	 * @param status
	 * @return
	 */
	public boolean setGpio(Reader reader, byte status);

	/**
	 * 获取GPIO电平
	 * 
	 * @param reader
	 * @return
	 */
	public Byte getGpio(Reader reader);

	/**
	 * 获取工作模式
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getWorkMode(Reader reader);

	/**
	 * 设置工作模式
	 * 
	 * @param reader
	 * @param setFrequencyParameters
	 * @return
	 */
	public boolean setWorkMode(Reader reader, byte[] setFrequencyParameters);

	/**
	 * 重启
	 * 
	 * @param reader
	 * @param module
	 * @return
	 */
	public boolean reStart(Reader reader, int module);

	/**
	 * 数据通信方式
	 * 
	 * @param reader
	 * @param pattern
	 * @return
	 */
	public boolean setTransferPattern(Reader reader, int pattern);

	/**
	 * 蜂鸣器
	 * 
	 * @param reader
	 * @param state
	 * @return
	 */
	public boolean setBuzzerState(Reader reader, int state);

	/**
	 * 开启载波测试
	 * 
	 * @param reader
	 * @return
	 */
	public boolean startCarrier(Reader reader);

	/**
	 * 停止载波测试
	 * 
	 * @param reader
	 * @return
	 */
	public boolean stopCarrier(Reader reader);

	/**
	 * 设置衰减系数
	 * 
	 * @param reader
	 * @param attenuation
	 * @return
	 */
	public boolean setAttenuation(Reader reader, int attenuation);

	/**
	 * 获取衰减系数
	 * 
	 * @param reader
	 * @return
	 */
	public String getAttenuation(Reader reader);

	/**
	 * 获取设备号
	 * 
	 * @param reader
	 * @return
	 */
	public String getDevID(Reader reader);

	/**
	 * 设置设备号
	 * 
	 * @param reader
	 * @param deviceNo
	 * @return
	 */
	public boolean setDevID(Reader reader, byte[] deviceNo);

	/**
	 * 获取网络参数
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getNet(Reader reader);

	/**
	 * 设置网络参数
	 * 
	 * @param reader
	 * @param netParam
	 * @return
	 */
	public boolean setNet(Reader reader, byte[] netParam);

	/**
	 * 恢复出厂设置
	 * 
	 * @param reader
	 * @return
	 */
	public boolean factoryReset(Reader reader);

	/**
	 * 获取wifi配置
	 * 
	 * @param reader
	 * @return
	 */
	public byte[] getWifi(Reader reader);

	/**
	 * 设置wifi配置
	 * 
	 * @param reader
	 * @param wifiParam
	 * @return
	 */
	public boolean setWifi(Reader reader, byte[] wifiParam);
	
	List<String> findSerialPorts();
}
