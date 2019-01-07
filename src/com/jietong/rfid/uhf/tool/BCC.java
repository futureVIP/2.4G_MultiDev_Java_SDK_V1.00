package com.jietong.rfid.uhf.tool;

import com.jietong.rfid.util.DataConvert;

public class BCC {
	
	public static void main(String []args){
		byte [] send = {0x20,0x18,(byte) 0x88,0x0E};
		System.out.println(DataConvert.bytesToHexString(checkSum(send)));
	}
	
	public static byte checkSum(byte[] sourceData) {
		byte result = 0x00;
		for (int i = 2; i < sourceData.length; i++) {
			result ^= sourceData[i];
		}
		return result;
	}
}
