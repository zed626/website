package com.aiwac.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.SSLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aiwac.constant.EmotionConstant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
*
* @author xy
* @date 2017年10月26日
* image recognize service
*/

public class FacePluesEmotionRecognizeUtil {
	private static final Logger logger = LogManager.getLogger(FacePluesEmotionRecognizeUtil.class);
	private static final String URL = "https://api-cn.faceplusplus.com/facepp/v3/detect";
	private static final String API_KEY = "gYcXT-MRwmm3ujYY-ShOE0tor5mMBRrr";
	private static final String API_SECRET = "1HtNNVb3sMgzMDTNAqT2y1BcY1bKIWwP";
	private static final String RETURN_ATTRIBUTES = "emotion";

	public static int faceDetect(byte[] faceData) {
		if(faceData == null) {
			return EmotionConstant.BAIDU_CALM_EMOTION;
		}
		
        //这里输入api_key、api_secret
        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", API_KEY);
        map.put("api_secret", API_SECRET);
        map.put("return_attributes", RETURN_ATTRIBUTES);
        
		HashMap<String, byte[]> byteMap = new HashMap<>();
        byteMap.put("image_file", faceData);
        
        //下面调用post、获得服务器的返回值
        //详细说明看文档  https://console.faceplusplus.com.cn/documents/4888373
        try{
            byte[] bacd = post(URL, map, byteMap);
            String str = new String(bacd);
            logger.info("face recognize result: " + str);
            ObjectMapper mapper = new ObjectMapper();
            try {
    			JsonNode node = mapper.readTree(str);
    			JsonNode faces = node.get("faces");
    			if(faces == null || !faces.has(0)) {
    				// 没有识别出人脸
    				return EmotionConstant.BAIDU_CALM_EMOTION;
    			}
    			JsonNode emotion = faces.get(0).get("attributes").get("emotion");

    			int sadness = emotion.get("sadness").asInt();
    			int neutral = emotion.get("neutral").asInt();
    			int disgust = emotion.get("disgust").asInt();
    			int anger = emotion.get("anger").asInt();
    			int surprise = emotion.get("surprise").asInt();
    			int fear = emotion.get("fear").asInt();
    			int happiness = emotion.get("happiness").asInt();
    			
    			int[] emotionArray = {sadness, neutral, disgust, anger, surprise, fear, happiness};
    			
    			Arrays.sort(emotionArray);
    			int maxEmotion = emotionArray[6];
    			int result = EmotionConstant.BAIDU_CALM_EMOTION;
    			if(maxEmotion == sadness)
    				result = EmotionConstant.BAIDU_NEGATIVE_EMOTION;
    			if(maxEmotion == neutral)
    				result = EmotionConstant.BAIDU_CALM_EMOTION;
    			if(maxEmotion == disgust)
    				result = EmotionConstant.BAIDU_NEGATIVE_EMOTION;
    			if(maxEmotion == anger)
    				result = EmotionConstant.BAIDU_NEGATIVE_EMOTION;
    			if(maxEmotion == surprise)
    				result = EmotionConstant.BAIDU_NEGATIVE_EMOTION;
    			if(maxEmotion == fear)
    				result = EmotionConstant.BAIDU_NEGATIVE_EMOTION;
    			if(maxEmotion == happiness)
    				result = EmotionConstant.BAIDU_POSITIVE_EMOTION;
    			
    			logger.info("face++ emotion recognize result: " + result);
    			return result;
            }catch (Exception e) {
				e.printStackTrace();
			}
        }catch (Exception e) {
        	e.printStackTrace();
		}
		return EmotionConstant.BAIDU_CALM_EMOTION;
	}
	
	//这里设置了超时函数
	private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    
    //返回一个随机的32位的字符串
    private static String boundaryString = getBoundary();
    
    private static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
        //conne是一个HTTP的处理类
    	HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
		Iterator iter = map.entrySet().iterator();
        //这里将map的值，一个一个提取出来
        while(iter.hasNext()){
			Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if(fileMap != null && fileMap.size() > 0){
            Iterator fileIter = fileMap.entrySet().iterator();
            while(fileIter.hasNext()){
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        
        
        InputStream ins = null;
        //服务器返回处理值
        int code = conne.getResponseCode();
        try{
            if(code == 200){
                ins = conne.getInputStream();
            }else{
                ins = conne.getErrorStream();
            }
        }catch (SSLException e){
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while((len = ins.read(buff)) != -1){
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }
	 
    /**
     * 
     * @return 返回一个随机的32位的字符串
     */
    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }
    
    //Translates a string into application/x-www-form-urlencoded format using a specific encoding scheme. 
    private static String encode(String value) throws Exception{
        return URLEncoder.encode(value, "UTF-8");
    }
    
}
