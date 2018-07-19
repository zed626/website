package com.aiwac.tool;

import com.aiwac.constant.Constant;

/**
*
* @author lmc
* @date 2017.10.24
* @todo sentIdentifyingCode to android using message
*/

public class IdentifyingCodeUtils {
	//todo 
	public static void sendCodeWithMsg(String code) {
		
	}
	
	public static String generateIdentifyCode() {
		// Code range 1000-9999
		int code = (int) (Math.random() * Constant.IDENTIFYINGCODERANGE) + Constant.IDENTIFYINGCODEBASENUM;
		System.out.println("generate the random number : " + String.valueOf(code));
		return String.valueOf(code);
	}
}
