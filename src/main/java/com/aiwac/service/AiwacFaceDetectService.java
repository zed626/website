package com.aiwac.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aiwac.utils.AiwacFaceEmotionRecognizeUtil;

/** 
 *   
 * OpenCV3.4 FaceDetect 
 * 
 */  


public class AiwacFaceDetectService
{  
	
	private static final Logger logger = LogManager.getLogger(AiwacFaceEmotionRecognizeUtil.class);
    /** 
     * 识别指定图片上的所有人脸 
     * @param inImg 输入原图 的地址
     * @param 图片上有没有人脸
     */  
	static{
		logger.info("load native lib");  
        System.load("/usr/local/mtcnn/libaiwacfacedetect.so");
        init();
        logger.info("init finish");  
	}
	public static native void init();
    public static native int detectFace(String inImg);
}  

