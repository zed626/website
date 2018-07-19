package com.aiwac.constant;

import java.util.concurrent.TimeUnit;

/**
*
* @author HC
* @date 2018年1月11日
*
*/

public class ThreadPoolConstant {
	// baidu speech to text
	public static final int STT_THREAD_POOL_CORE_POOL_SIZE = 10;
	public static final int STT_THREAD_POOL_MAX_POOL_SIZE = STT_THREAD_POOL_CORE_POOL_SIZE + 10;
	public static final int STT_THREAD_POOL_KEEP_ALIVE_TIME = 60;
	public static final TimeUnit STT_THREAD_POOL_TIME_UNIT = TimeUnit.SECONDS;
	
	// baidu text to speech
	public static final int TTS_THREAD_POOL_CORE_POOL_SIZE = 10;
	public static final int TTS_THREAD_POOL_MAX_POOL_SIZE = STT_THREAD_POOL_CORE_POOL_SIZE + 10;
	public static final int TTS_THREAD_POOL_KEEP_ALIVE_TIME = 60;
	public static final TimeUnit TTS_THREAD_POOL_TIME_UNIT = TimeUnit.SECONDS;
	
	// qizhi conversation
	public static final int QIZHI_THREAD_POOL_CORE_POOL_SIZE = 10;
	public static final int QIZHI_THREAD_POOL_MAX_POOL_SIZE = STT_THREAD_POOL_CORE_POOL_SIZE + 10;
	public static final int QIZHI_THREAD_POOL_KEEP_ALIVE_TIME = 60;
	public static final TimeUnit QIZHI_THREAD_POOL_TIME_UNIT = TimeUnit.SECONDS;
	
	// turing conversation
	public static final int TURING_THREAD_POOL_CORE_POOL_SIZE = 10;
	public static final int TURING_THREAD_POOL_MAX_POOL_SIZE = STT_THREAD_POOL_CORE_POOL_SIZE + 10;
	public static final int TURING_THREAD_POOL_KEEP_ALIVE_TIME = 60;
	public static final TimeUnit TURING_THREAD_POOL_TIME_UNIT = TimeUnit.SECONDS;
}
