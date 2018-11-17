package com.jietong.rfid.uhf.tool;

/**
 * 分立器件命令
 * 
 * @author zhuQixiang
 * 
 */
public class CMDSend {
	/**
	 * 发送命令起始码
	 */
	public static final byte[] ACTIVE_START_CODE = { 0x43, 0x4D };
	/**
	 * 获取设备版本号
	 */
	public static final byte[] ACTIVE_GET_VERSION = { 0x01, 0x02 };

	/**
	 * 设置GPIO电平
	 */
	public static final byte[] ACTIVE_SET_GPIO = { 0x02, 0x02 };
	/**
	 * 获取GPIO电平
	 */
	public static final byte[] ACTIVE_GET_GPIO = { 0x03, 0x02 };
	/**
	 * 设置参数信息
	 */
	public static final byte[] ACTIVE_SET_PARAMETERS = { 0x45, (byte) 0x02 };
	/**
	 * 获取参数信息
	 */
	public static final byte[] ACTIVE_GET_PARAMETERS = { 0x4D, 0x02}; 
	/**
	 * 读写器及模块复位
	 */
	public static final byte[] ACTIVE_RESET = { (byte) 0x05, 0x02 };
	/**
	 * 数据通信方式
	 */
	public static final byte[]  ACTIVE_SET_TRANSFER_MODE = {0x1C,0x02}; 
	/**
	 * 蜂鸣器
	 */
	public static final byte[] ACTIVE_BUZZER = {(byte) 0x19,0x02};
	/**
	 * 开启载波测试
	 */
	public static final byte[] ACTIVE_START_CARRIER = {(byte) 0x46,0x02};
	 /**
	  * 停止载波测试
	  */
	public static final byte[] ACTIVE_STOP_CARRIER = {(byte) 0x47,0x02};
	/**
	 * 获取衰减系数
	 */
	public static final byte[] ACTIVE_GET_ATTENUATION = {0x4A,0x02};
	/**
	 * 设置衰减系数
	 */
	public static final byte[] ACTIVE_SET_ATTENUATION = {0x49,0x02};
	/**
	 * 设置设备号
	 */
	public static final byte[] ACTIVE_SET_DEVID = { (byte) 0x1A, 0x02 };
	/**
	 * 获取设备号
	 */
	public static final byte[] ACTIVE_GET_DEVID = { (byte) 0x1B, 0x02 };
	/**
	 * 获取网络参数
	 */
	public static final byte[] ACTIVE_GET_NET_CONFIG = {(byte) 0x04,0x02};
	/**
	 * 设置网络参数
	 */
	public static final byte[] ACTIVE_SET_NET_CONFIG = {(byte) 0x06,0x02};
	/**
	 * 恢复出厂设置
	 */
	public static final byte[] ACTIVE_FACTORY_RESET = {(byte) 0x0E,0x02};
	/**
	 * 设置wifi配置
	 */
	public static final byte[] ACTIVE_SET_WIFI_CONFIG = {(byte) 0x10,0x02};
	/**
	 * 获取wifi配置
	 */
	public static final byte[] ACTIVE_GET_WIFI_CONFIG = {(byte) 0x12,0x02};
	/**
	 * 连续寻卡
	 */
	public static final byte[] ACTIVE_MULITI_ID = {0x41,0x02};
	/**
	 * 停止寻卡
	 */
	public static final byte[] ACTIVE_STOP_READ_TAG = {0x18,0x02};
}
