package com.aiwac.constant;

/**
*
* @author HC
* @date 2017年11月1日
*
*/

public class BusinessConstant {
	// business type
	public static final String CLOCK_SYNCHRONIZATION_REQUEST = "0003";
	public static final String CONVERSATION_REQUEST = "0004";
	public static final String NOTIFICATION_PUSH_REQUEST = "0005";
	public static final String INFORMATION_QUERY_REQUEST = "0006";
	public static final String EMOTION_TAG_REQUEST = "0007";
	public static final String ALARM_SET_REQUEST = "0008";
	public static final String PUSH_RETRANSMISSION_MESSAGE = "0009";
	public static final String MONITOR_SETTINT_REQUEST = "0010";
	public static final String ACTIVE_PLAYING_VOICE_REQUEST = "0012";
	public static final String ACTIVE_PLAYING_GAME_REQUEST = "0013";
	public static final String MONITOR_INFORMATION_REQUEST = "0014";
	public static final String MONITOR_NOTIFICATION_PUSH_REQUEST = "0015";
	public static final String MONITOR_INFORMATION_QUERY_REQUEST = "0016";
	public static final String ALARM_SYNCHRONIZE_REQUEST = "0018"; 
	public static final String EMOTION_SHEET_QUERY = "0019";
	public static final String ACTIVE_CONVERSATION_ENQUIRE = "0020";
	public static final String INTERACTION_CONTENT_REQUEST = "0021";
	public static final String PERSONALITYTYPE_SETTING_REQUEST = "0022";
	public static final String THERMALIMAGERY_EMOTION_REQUEST = "9999";
	
	public static final String AIWAC_CHAT_TRACK_USER_PREFIX = "ChatWith.";
	
	
	// error code
	public static final String RIGHT_RESPONSE_CODE = "200";
	public static final String VOICE_RECOGNIZE_FAILURE = "201";
	public static final String ROBOT_OFFLINE_FAILURE = "202";
	public static final String ALARM_SYNCHRONIZE_FAILURE = "201";
	public static final String FETCH_INTERACTION_CONTENT_FAILURE = "201";
	
	// response description
	public static final String BUSINESS_SUCCESS_DESC = "成功";
	public static final String BUSINESS_FAILURE_DESC = "失败";
	public static final String VOICE_RECOGNIZE_SUCCESS_DESC = "语音识别成功";
	public static final String VOICE_RECOGNIZE_FAILURE_DESC = "语音识别失败";
	public static final String CONVERSATION_NOTIFICATION_DESC = "您有一条新消息";
	public static final String MONITOR_NOTIFICATION_DESC = "小宝宝有新情况";
	public static final String ROBOT_OFFLINE_FAILURE_DESC = "机器人离线";
	public static final String FETCH_INTERACTION_CONTENT_FAILURE_DESC = "内容获取错误";
	
	// client type
	public static final String ROBOT_CLIENT_TYPE = "1";
	public static final String ANDROID_CLIENT_TYPE = "2";
	public static final String SERVER_CLIENT_TYPE = "3";
	public static final String IOS_CLIENT_TYPE = "4";
	
	// robot running mode
	public static final String CONVERSATION_RUNNING_MODE = "1";
	public static final String SLEEP_RUNNING_MODE = "2";
	public static final String MONITOR_RUNNING_MODE = "3";
	
	// command priority
	public static final String NOT_IMPORTANT_PRIORITY = "-1";
	public static final String NORMAL_PRIORITY = "0";
	public static final String IMPORTANT_PRIORITY = "1";
	
	// monitor operation Type
	public static final String TURN_ON_MONITOR = "1";
	public static final String TURN_OFF_MONITOR = "2";
	
	// interaction type
	public static final String PLAYING_MUSIC_TYPE = "1";
	public static final String PLAYING_STORY_TYPE = "2";
	public static final String PLAYING_VOICE_GAME_TYPE = "3";
	public static final String PLAYING_TACTILE_GAME_TYPE = "4";
}
