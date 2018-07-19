package com.aiwac.tool;
/**
*
* @author LMC
* @date 2017年10月31日
*
*/
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getDataInfo() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());
	}
	
	public static String getData() {
		SimpleDateFormat df = new SimpleDateFormat("/yyyy/MM/dd/");
		return df.format(new Date());
	}
	
	public static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("HHmmssSSS");
		return df.format(new Date());
	}
	
	
	public static String getBusinessTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return df.format(new Date());
	}
}
