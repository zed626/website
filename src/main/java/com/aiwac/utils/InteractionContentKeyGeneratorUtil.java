package com.aiwac.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
*
* @author HC
* @date 2018年1月15日
*
*/

public class InteractionContentKeyGeneratorUtil {
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyMMdd");
	private static final String TODAY_LAST_TIME = "235959";
	private static final int INDEX_BITS = 6;
	private static final AtomicInteger atomicInteger = new AtomicInteger(1);
	private static long todayLastMillSecond = 0;
	private static String keyPrefix;
	
	public static String nextKey() throws ParseException {
		long nowTime = System.currentTimeMillis();
		
		synchronized(InteractionContentKeyGeneratorUtil.class) {
			if (nowTime > todayLastMillSecond) {
				Date date = new Date(nowTime);
				keyPrefix = SIMPLE_DATE_FORMAT.format(date);
				
				// update todayLastMillSecond
				String tempKeyPrefix = keyPrefix + TODAY_LAST_TIME;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
				long lastMillSecond = simpleDateFormat.parse(tempKeyPrefix).getTime();
				todayLastMillSecond = lastMillSecond;
				
				// update AtomicInteger initial number
				atomicInteger.set(1);
			}
		}
		
		return keyPrefix + nextNum();
	}
	
	private static String nextNum() {
		int num = atomicInteger.getAndIncrement();
		String numStr = String.valueOf(num);
		int len = numStr.length();
		
		if (len < INDEX_BITS) {
			for (int i = 0; i < INDEX_BITS - len; i++) {
				numStr = "0" + numStr;
			}
		}
		
		return numStr;
	}
	
	public static void setInitialNumber(int initialNum) {
		atomicInteger.set(initialNum);
	}
	
	public static void setKeyPrefix(String prefix) {
		keyPrefix = prefix;
	}
	
	public static void setTodayLastMillSecond(long time) {
		todayLastMillSecond = time;
	}
}
