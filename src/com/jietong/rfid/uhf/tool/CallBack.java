package com.jietong.rfid.uhf.tool;



/**
 * ��ȡ��ȡ���ݽӿ�
 * 
 * @author zhuQixiang createDate 2017-10-25
 * 
 */
public interface CallBack {
	/**
	 * ѭ����������Ѱ��һ�λص�����
	 * 
	 * @param data
	 *            ���ӱ�ǩ����
	 * @param antNo
	 *            ���߱��s
	 */
	void getReadData(String data, int antNo,String deviceId);
}