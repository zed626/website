package com.aiwac.utils;
/**
*
* @author XY
* @date 2017年11月16日
* @description 
*
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class CommonUtil {
	private static final Logger logger = LogManager.getLogger(CommonUtil.class);
	private static final String macAddressRegex = "^([0-9a-fA-F]{2})(([/\\s:-][0-9a-fA-F]{2}){5})$";
	
	public static boolean isRobot(String userNumber) {
		if (userNumber.matches(macAddressRegex)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getUniqueID() {
		String uniqueID = UUID.randomUUID().toString();
		logger.info("generate uniqueID: {}", uniqueID);
		return uniqueID;
	}
	
	public static int getRandomInteger(int bound) {
		if (bound > 0) {
			int randomInteger = (new Random()).nextInt(bound);
			logger.info("generate random integer: {}", randomInteger);
			return randomInteger;
		} else {
			logger.error("bound must be positive, error bound: {}", bound);
			return -1;
		}
	}

	public static String getBase64StrFromFile(String filePath) {
		File file = new File(filePath);
		return getBase64StrFromFile(file);
	}
	
	public static String getBase64StrFromFile(File file) {
		String base64Str = null;
		InputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			byte[] fileBytes = IOUtils.toByteArray(fileInputStream);
			return getBase64StrFromFile(fileBytes);
		} catch (IOException e) {
			LoggerUtil.LogException(logger, e);
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				LoggerUtil.LogException(logger, e);
			}
		}
		return base64Str;
	}
	
	public static String getBase64StrFromFile(MultipartFile file) {
		try {
			return getBase64StrFromFile(file.getBytes());
		} catch (IOException e) {
			LoggerUtil.LogException(logger, e);
		}
		
		return "";
	}
	
	public static String getBase64StrFromFile(byte[] fileBytes) {
		return Base64.encodeBase64String(fileBytes);
	}
	
	public static String encodeUtf8ByteString(String string, String regex) {
		byte[] utf8ByteArray = null;
		try {
			utf8ByteArray = string.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtil.LogException(logger, e);
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder(utf8ByteArray.length);
		
		for (byte num : utf8ByteArray) {
			stringBuilder.append(num).append(regex);
		}
		
		return stringBuilder.toString();
	}
	
	public static String decodeUtf8ByteString(String string, String regex) {
		String result = "";
		String[] byteString = string.split(regex);
		int len = byteString.length;
		byte[] byteArray = new byte[len];
		
		for (int i = 0; i < len ; i++) {
			byteArray[i] = Byte.valueOf(byteString[i]);
		}
		
		try {
			result = new String(byteArray, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtil.LogException(logger, e);
		}
		
		return result;
	}
}

