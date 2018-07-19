package com.aiwac.constant;

/**
*
* @author HC
* @date 2017年11月14日
*
*/

public class EmotionConstant {
	public static final int ACTIVATE_CONVERSATION_THRESHOLD = -1;
	
	public static final int FACE_IMAGE_EMOTION_SOURCE = 1;
	public static final int VOICE_EMOTION_SOURCE = 2;
	public static final int TEXT_EMOTION_SOURCE = 3;
	public static final int THERMAL_IMAGE_EMOTION_SOURCE = 4;
	
	public static final int BAIDU_NEGATIVE_EMOTION = -1;
	public static final int BAIDU_CALM_EMOTION = 0;
	public static final int BAIDU_POSITIVE_EMOTION = 1;
	
	public static final int BAIDU_EMOTION_TYPES = 3;
	public static final String BAIDU_CALM_EMOTION_TYPE = "6";
	public static final String BAIDU_HAPPY_EMOTION_TYPE = "3";
	public static final String BAIDU_SAD_EMOTION_TYPE = "4";
	
	public static final int AIWAC_EMOTION_TYPES = 7;
	public static final String AIWAC_ANGRY_EMOTION_TYPE = "0";
	public static final String AIWAC_DISGUST_EMOTION_TYPE = "1";
	public static final String AIWAC_FEAR_EMOTION_TYPE = "2";
	public static final String AIWAC_HAPPY_EMOTION_TYPE = "3";
	public static final String AIWAC_SAD_EMOTION_TYPE = "4";
	public static final String AIWAC_SURPRISE_EMOTION_TYPE = "5";
	public static final String AIWAC_NEUTRAL_EMOTION_TYPE = "6";
	
	// emotion bits, 7 '_' represent 7 kinds of emotion type
	public static final String EMOTION_BIT = "_______";
	public static final String CONTENT_GROUP_DEFAULT_EMOTION = "2222222";
	public static final String[] EMOTION_TYPES = {"Angry", "Disgust", "Fear", "Happy", "Sad", "Surprise", "Neutral"};
}
