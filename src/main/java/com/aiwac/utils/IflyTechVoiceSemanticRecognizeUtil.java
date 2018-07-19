//package com.aiwac.utils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.security.MessageDigest;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
//*
//* @author HC
//* @date 2017年10月26日
//*
//*/
//
//public class IflyTechVoiceSemanticRecognizeUtil {
//	private static final Logger logger = LogManager.getLogger(IflyTechVoiceSemanticRecognizeUtil.class);
//	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
//			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
//	private static final String xAppid = "5a17bf46";
//	private static final String apiKey = "63f15bf7883c40d0b835a59a05360fa7";
//	
//	public static List<String> getVoiceSemantic(byte[] bytes, String userid) {
//		long time = System.currentTimeMillis() / 1000;
//		String curTime = String.valueOf(time);
//		String xParam = "{\"auf\":\"16k\",\"aue\":\"raw\",\"scene\":\"main\",\"userid\":\"" + userid + "\"}";
//		String xParamBase64 = getBase64(xParam);
//
//		String fileData = null;
//
//		fileData = Base64.encodeBase64String(bytes);
//		fileData = "data=" + fileData;
//		
//		String token = apiKey + curTime + xParamBase64 + fileData;
//		String xCheckSum = md5Encode(token);
//		String resBody = "";
//		PrintWriter out = null;
//		BufferedReader in = null;
//		try {
//			String url = "https://api.xfyun.cn/v1/aiui/v1/voice_semantic";
//			URL realUrl = new URL(url);
//			HttpURLConnection conn = (HttpURLConnection) realUrl
//					.openConnection();
//			conn.setReadTimeout(2000);
//			conn.setConnectTimeout(1000);
//			conn.setRequestMethod("POST");
//
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			conn.setRequestProperty("X-Appid", xAppid);
//			conn.setRequestProperty("X-CurTime", curTime);
//			conn.setRequestProperty("X-Param", xParamBase64);
//			conn.setRequestProperty("X-CheckSum", xCheckSum);
//			conn.setRequestProperty("Connection", "keep-alive");
//			conn.setRequestProperty("Content-type",
//					"application/x-www-form-urlencoded; charset=utf-8");
//
//			out = new PrintWriter(conn.getOutputStream());
//			out.print(fileData);
//			out.flush();
//
//			InputStream inputStream = conn.getInputStream();
//			InputStreamReader inputStreamReader = new InputStreamReader(
//					inputStream, "utf-8");
//			in = new BufferedReader(inputStreamReader);
//			String line;
//			while ((line = in.readLine()) != null) {
//				resBody += line;
//			}
//			logger.info("iflytech voice recognize result: {}", resBody);
//			return getAnswer(resBody);
//		} catch (Exception e) {
//			LoggerUtil.LogException(logger, e);
//		} finally {
//			try {
//				if (out != null) {
//					out.close();
//				}
//				if (in != null) {
//					in.close();
//				}
//			} catch (IOException ex) {
//				LoggerUtil.LogException(logger, ex);
//			}
//		}
//		return new ArrayList<>();
//	}
//
//	/**
//	 * Base64编码
//	 */
//	public static String getBase64(String str) {
//		if (str == null || "".equals(str)) {
//			return "";
//		}
//		try {
//			byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
//			str = new String(encodeBase64);
//		} catch (UnsupportedEncodingException e) {
//			LoggerUtil.LogException(logger, e);
//		}
//		return str;
//	}
//
//	/**
//	 * md5加密
//	 */
//	public static String md5Encode(String source) {
//		String result = null;
//		try {
//			result = source;
//			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//			messageDigest.update(result.getBytes("utf-8"));
//			result = byteArrayToHexString(messageDigest.digest());
//		} catch (Exception e) {
//			LoggerUtil.LogException(logger, e);
//		}
//		return result;
//	}
//
//	private static String byteArrayToHexString(byte[] bytes) {
//		StringBuilder stringBuilder = new StringBuilder();
//		for (byte tem : bytes) {
//			stringBuilder.append(byteToHexString(tem));
//		}
//		return stringBuilder.toString();
//	}
//
//	private static String byteToHexString(byte b) {
//		int n = b;
//		if (n < 0) {
//			n = 256 + n;
//		}
//		int d1 = n / 16;
//		int d2 = n % 16;
//		return hexDigits[d1] + hexDigits[d2];
//	}
//	
//	private static List<String> getAnswer(String jsonString) {
//		List<String> result = new ArrayList<>(2);
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			JsonNode node = mapper.readTree(jsonString);
//			JsonNode code = node.get("code");
//
//			if("\"00000\"".equals(code.toString())) {
//				JsonNode data = node.get("data");
//				result.add(data.get("text").toString().replace("\"", ""));
//				JsonNode rc = data.get("rc");
//				if("0".equals(rc.toString())) {
//					String answer = parserService(data);
//					result.add(answer);
//				}else {
//					logger.error("iflytech error rc: {}", rc.toString());
//				}
//			}else {
//				logger.error("iflytech error code: {}", code.toString());
//			}
//		} catch (IOException e) {
//			LoggerUtil.LogException(logger, e);
//		}
//		return result;
//	}
//	
//	private static String parserService(JsonNode data) {
//		JsonNode service = data.get("service");
//		String res = "";
//		
//		switch (service.toString()) {
//			case "\"openQA\"":
//				res = parserOpenQA(data);
//				break;
//			case "\"baike\"":
//				res = parserBaike(data);
//				break;
//			case "\"datetime\"":
//				res = parserDatetime(data);
//				break;
//			case "\"calc\"":
//				res = parserCalc(data);
//				break;
//			case "\"joke\"":
//				
//				break;
//			case "\"musicX\"":
//				
//				break;
//			case "\"poetry\"":
//				
//				break;
//			case "\"story\"":
//				
//				break;
//			case "\"weather\"":
//				res = parserWeather(data);
//				break;
//			default:
//				logger.warn("don not support scene: {}", service.toString());
//				break;
//		}
//		
//		return res;
//	}
//	
//	private static String parserOpenQA(JsonNode data) {
//		JsonNode answer = data.get("answer");
//		String res = answer.get("text").toString();
//		return res.substring(1, res.length() - 1);
//	}
//	
//	private static String parserBaike(JsonNode data) {
//		return parserOpenQA(data);
//	}
//	
//	private static String parserDatetime(JsonNode data) {
//		return parserOpenQA(data);
//	}
//	
//	private static String parserCalc(JsonNode data) {
//		return parserOpenQA(data);
//	}
//	
//	private static String parserWeather(JsonNode data) {
//		JsonNode answer = data.get("answer");
//		String res = answer.get("text").toString();
//		res = res.replace("\"", "");
//		res = res.replace("\\", "");
//		return res;
//	}
//}
