package com.jietong.rfid.uhf.tool;

public class CMDReceive {
	public static final int HEAD_LENGTH = 6;
	/**
	 * 查询版本号
	 */
	public static final byte[] ACTIVE_GET_VERSION = { (byte) 0x81, 0x03 };
	/**
	 * 设置GPIO电平
	 */
	public static final byte[] ACTIVE_SET_GPIO = { (byte) 0x82, 0x03 };
	/**
	 * 获取GPIO电平
	 */
	public static final byte[] ACTIVE_GET_GPIO = { (byte) 0x83, 0x03 };
	/**
	 * 设置参数信息
	 */
	public static final byte[] ACTIVE_SET_PARAMETERS = { (byte) 0xC5, 0x03 };
	/**
	 * 获取参数信息
	 */
	public static final byte[] ACTIVE_GET_PARAMETERS = { (byte) 0xCD, 0x03 };

	/**
	 * 读写器及模块复位
	 */
	public static final byte[] ACTIVE_RESET = { (byte) 0x85, 0x03 };

	/**
	 * 数据通信方式
	 */
	public static final byte[] ACTIVE_SET_TRANSFER_MODE = { (byte) 0x9C, 0x03 };
	/**
	 * 蜂鸣器
	 */
	public static final byte[] ACTIVE_BUZZER = { (byte) 0x99, 0x03 };
	/**
	 * 开启载波测试
	 */
	public static final byte[] ACTIVE_START_CARRIER = { (byte) 0xC6, 0x03 };
	/**
	 * 停止载波测试
	 */
	public static final byte[] ACTIVE_STOP_CARRIER = { (byte) 0xC7, 0x03 };
	/**
	 * 获取衰减系数
	 */
	public static final byte[] ACTIVE_GET_ATTENUATION = { (byte) 0xCA, 0x03 };
	/**
	 * 设置衰减系数
	 */
	public static final byte[] ACTIVE_SET_ATTENUATION = { (byte) 0xC5, 0x03 };
	/**
	 * 设置设备号
	 */
	public static final byte[] ACTIVE_SET_DEVID = { (byte) 0x9A, 0x03 };
	/**
	 * 获取设备号
	 */
	public static final byte[] ACTIVE_GET_DEVID = { (byte) 0x9B, 0x03 };
	/**
	 * 获取网络参数
	 */
	public static final byte[] ACTIVE_GET_NET_CONFIG = {(byte) 0x84,0x03};
	/**
	 * 设置网络参数
	 */
	public static final byte[] ACTIVE_SET_NET_CONFIG = {(byte) 0x86,0x03};
	/**
	 * 恢复出厂设置
	 */
	public static final byte[] ACTIVE_FACTORY_RESET = {(byte) 0x8E,0x03};
	/**
	 * 设置wifi配置
	 */
	public static final byte[] ACTIVE_SET_WIFI_CONFIG = {(byte) 0x90,0x03};
	/**
	 * 获取wifi配置
	 */
	public static final byte[] ACTIVE_GET_WIFI_CONFIG = {(byte) 0x92,0x03};
	/**
	 * 连续寻卡
	 */
	public static final byte[] ACTIVE_MULITI_ID = {(byte) 0xC1,0x03};
}
