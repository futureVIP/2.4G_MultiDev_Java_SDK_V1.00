package com.jietong.rfid.util;

import com.jietong.rfid.uhf.entity.Reader;
import com.jietong.rfid.uhf.service.ReaderService;
import com.jietong.rfid.uhf.service.impl.ReaderServiceImpl;

public class ReaderUtil {
	
	public static ReaderService readerService = new ReaderServiceImpl();

	public static int connectCount = 0;
	/**
	 * ���������豸����
	 */
	public final static int MAX_DEVICE_NUM = 50;
	/**
	 * ���Ӷ�������dis
	 */
	public final static Reader[] readers = new Reader[MAX_DEVICE_NUM];
}
