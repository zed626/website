package com.aiwac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aiwac.bean.request.WeChatBusiness;
import com.aiwac.constant.Constant;
import com.aiwac.service.AiwacFaceDetectService;
import com.aiwac.service.FaceDetectService;
import com.aiwac.service.FileDetailService;
import com.aiwac.service.WechatBusinessSqlService;
import com.aiwac.tool.DateUtils;
import com.aiwac.tool.FileUtils;
import com.aiwac.tool.UuidUtils;
import com.aiwac.utils.AiwacFaceEmotionRecognizeUtil;
import com.aiwac.utils.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class WechatUpload {
	
	private static final Logger logger = LogManager.getLogger(WechatUpload.class);
	
	@Autowired
	private FileDetailService fileDetailService;
	
	@Autowired
	private WechatBusinessSqlService WechatBusinessSqlService;
	
		
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody String data) throws UnsupportedEncodingException{
        logger.info("get wechat data: " + data);
        String res = URLDecoder.decode(data, "UTF-8");
        logger.info("resolve with ut: " + res);
        WeChatBusiness now = (WeChatBusiness) JsonUtil.validateJsonStr(res, WeChatBusiness.class);      
        byte[] picByte = Base64.decodeBase64(now.getData().replaceAll(" ","+"));
        String business_id = now.getBusiness_id();
        String u_id = now.getUser_id();
        int s_id = now.getScene_id();
        int seriel = now.getSeriel();
        int expectemotion = now.getLabel();
        String platform  = now.getPlatform();           
	   	String uuid = UuidUtils.getUUID().substring(10, 14);
   		//String rootPath = "F:\\aiwac\\h5image\\";   //windows
   		String rootPath = "/home/aiwac/apache-tomcat-8.0.47/webapps/website/";   //linux
   		String filePath = "resources/uploadimage/"+platform+DateUtils.getData();  //linux
   		//String filePath = platform+DateUtils.getData();  
   		String fileName = fileDetailService.generateFileName(DateUtils.getTime()+uuid,0,Constant.JPG);
   		FileUtils.convertByteToFile(picByte,rootPath+filePath, fileName);
   		//int flags = AiwacFaceDetectService.detectFace(rootPath+filePath+fileName);
   		int flags = FaceDetectService.detectFace(rootPath+filePath+fileName);
   		logger.info("numbers:  " + flags);
   		if(flags>=1) {
	   		float max = 0;
	   		int index = 0;
	   		float[] faceEmotionProbabilities = AiwacFaceEmotionRecognizeUtil.labelImage(uuid, picByte);
	   		for(int i = 0;i<faceEmotionProbabilities.length;i++)
	   		{
	   			if(faceEmotionProbabilities[i]>max) {
	   				max = faceEmotionProbabilities[i];
	   				index=  i;
	   			}
	   		}
	   		logger.info("emotion recgnize is: " + index);
	   		int score = (int) (faceEmotionProbabilities[expectemotion]*25);
	   		WechatBusinessSqlService.saveBusinessInSql(business_id, u_id, s_id, filePath+fileName, score, platform, seriel, index, expectemotion);
	   		//wechatService.savePhotoDataInSql(uuid, String.valueOf(index), "0", id, filePath+fileName);
	   		if(score>=1){
	   			logger.info("response normal data to client");
	   			return "{"+"\"emotion\":\""+index+"\",\"path\":\""+filePath+fileName+"\",\""+"score\":\""+score+"\"}";
			}
	   		logger.info("response 1 to client");
	   		return "{"+"\"emotion\":\""+index+"\",\"path\":\""+filePath+fileName+"\",\""+"score\":\""+1+"\"}";
   		}
   		else {
   			logger.info("no face detected!!");
   			return "{"+"\"emotion\":\""+-1+"\",\"score\":\""+-1+"\"}";
   		}
    }
    
    
    /*
  //上传文件会自动绑定到MultipartFile中
    @RequestMapping(value="/",method=RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,
    		@RequestParam(value="id",required=false) String id,
    		@RequestParam(value="emotion",required=false) String emotion) throws Exception {

       //如果文件不为空，写入上传路径
       if(!file.isEmpty()) {
           //上传文件路径
           String path = request.getServletContext().getRealPath("/images/");
           //上传文件名
           String filename = file.getOriginalFilename();
           File filepath = new File(path,filename);
           //判断路径是否存在，如果不存在就创建一个
           if (!filepath.getParentFile().exists()) { 
               filepath.getParentFile().mkdirs();
           }
           //将上传文件保存到一个目标文件当中  
    	   	String uuid = UuidUtils.getUUID();
	   		//String filePath = "F:\\WX\\";   //windows
	   		String filePath = "/home/aiwac/wechat/";  
	   		String fileName = fileDetailService.generateFileName(uuid,0,Constant.JPG);
	   		FileUtils.convertByteToFile(file.getBytes(),"/home/aiwac/wechat/", fileName);
	   		float[] faceEmotionProbabilities = AiwacFaceEmotionRecognizeUtil.labelImage(uuid, file.getBytes());
	   		logger.info("emotion recgnize is: " + faceEmotionProbabilities);
	   		
	   		
	   		wechatService.savePhotoDataInSql(uuid, emotion, "0", id, filePath+fileName);
	   		
	   		logger.info("get wechat id: " + id);
	   		
           return "upload succuss";
       } else {
           return "upload error";
       }

    }
    */
}