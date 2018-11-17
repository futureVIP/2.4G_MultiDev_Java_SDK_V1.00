package com.jietong.rfid.util;

import com.jietong.rfid.uhf.entity.Reader;
import com.jietong.rfid.uhf.service.ReaderService;
import com.jietong.rfid.uhf.service.impl.ReaderServiceImpl;

public class ReaderUtil {
	
	public static ReaderService readerService = new ReaderServiceImpl();

	public static int connectCount = 0;
	/**
	 * 限制连接设备数量
	 */
	public final static int MAX_DEVICE_NUM = 50;
	/**
	 * 连接对象数组dis
	 */
	public final static Reader[] readers = new Reader[MAX_DEVICE_NUM];
}
