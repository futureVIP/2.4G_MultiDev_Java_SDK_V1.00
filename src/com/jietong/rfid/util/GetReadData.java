package com.jietong.rfid.util;

import com.jietong.rfid.uhf.entity.Reader;


/**
 * ��ȡ��ȡ���ݽӿ�
 * 
 * @author zhuQixiang createDate 2017-10-25
 * 
 */
public interface GetReadData {
	/**
	 * ѭ����������Ѱ��һ�λص�����
	 * 
	 * @param data
	 *            ���ӱ�ǩ����
	 * @param antNo
	 *            ���߱��
	 */
	void getReadData(Reader dis,String data, int antNo);
}
