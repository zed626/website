package com.aiwac.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aiwac.bean.request.WeChatSceneSum;
import com.aiwac.model.WeChatPicture;
import com.aiwac.model.WeChatScene;
import com.aiwac.service.WechatPictureSqlService;
import com.aiwac.service.WechatSceneSqlService;
import com.aiwac.tool.DateUtils;
import com.aiwac.tool.FileUtils;
import com.aiwac.tool.UuidUtils;
import com.aiwac.utils.JsonUtil;

/**
 *
 * @author HC
 * @date 2017年12月18日
 *
 */

@Controller
@RequestMapping("/scene")
public class SceneController {

	private static final Logger logger = LogManager.getLogger(SceneController.class);

	@Autowired
	WechatSceneSqlService wechatSceneSqlService;

	@Autowired
	WechatPictureSqlService wechatPictureSqlService;

	@ResponseBody
	@RequestMapping(value = { "/getscenesbypage" }, method = RequestMethod.GET)
	public String WechatSignin
			(@RequestParam(value = "page", required = true) int page) {
		WeChatSceneSum sum = new WeChatSceneSum();
		String businesscode = DateUtils.getBusinessTime() + UuidUtils.getUUID().substring(0, 5);
		sum.setBusinesscode(businesscode);
		int size=10;
		if(page<=0) page=1;
		int sceneCount= wechatSceneSqlService.getSceneCountInSql();
		int totalpages = (int)Math.ceil(sceneCount/(size*1.0));
        if (page > totalpages){
            page = totalpages;
        }  
		sum.setScenes(wechatSceneSqlService.getSceneByPageInSql((page-1)*size, size));
		sum.setPagecount(totalpages);
		sum.setPageindex(page);
		logger.info("return scens:" + sum);
		return JsonUtil.writeValueAsString(sum); 
	}
	
	@ResponseBody
	@RequestMapping(value = { "/getscenes" }, method = RequestMethod.GET)
	public String WechatSignin() {
		WeChatSceneSum sum = new WeChatSceneSum();
		String businesscode = DateUtils.getBusinessTime() + UuidUtils.getUUID().substring(0, 5);
		sum.setBusinesscode(businesscode);
		sum.setScenes(wechatSceneSqlService.getAvailableSceneInSql());
		logger.info("return scens:" + sum);
		return JsonUtil.writeValueAsString(sum); 
	}

	@RequestMapping("/add")
	public String add() {
		return "sceneControl/addScene";
	}

	@RequestMapping("/addScene")
	public String addScene(@RequestParam(value = "picture1", required = false) MultipartFile file1,
			@RequestParam(value = "picture2", required = false) MultipartFile file2,
			@RequestParam(value = "picture3", required = false) MultipartFile file3,
			@RequestParam(value = "picture4", required = false) MultipartFile file4,
			@RequestParam(value = "picture5", required = false) MultipartFile file5,
			@RequestParam(value = "picture6", required = false) MultipartFile file6,
			@RequestParam(value = "picture7", required = false) MultipartFile file7,
			@RequestParam(value = "music", required = false) MultipartFile music,
			@RequestParam(value = "label1", required = false) int label1,
			@RequestParam(value = "label2", required = false) int label2,
			@RequestParam(value = "label3", required = false) int label3,
			@RequestParam(value = "label4", required = false) int label4,
			@RequestParam(value = "label5", required = false) int label5,
			@RequestParam(value = "label6", required = false) int label6,
			@RequestParam(value = "label7", required = false) int label7,
			@RequestParam(value = "number", required = false) int number,
			@RequestParam(value = "name", required = false) String name) throws IOException {

		WeChatScene scene = new WeChatScene();
		name = new String(name.getBytes("ISO8859-1"), "utf-8");
		scene.setAvailable(1);
		scene.setName(name.split("\\.")[1]);
		logger.info(file7);
		logger.info(music);
		scene.setNumber(number);
		if (!music.isEmpty()) {
			//String rootPath = "F:\\aiwac\\h5image\\"; // windows
			String rootPath = "/home/aiwac/apache-tomcat-8.0.47/webapps/website/";  //linux
			String filePath = "resources/sceneimages/"+name.split("\\.")[0]+"/";
			//String filePath = number + "\\";
			String fileName = music.getOriginalFilename();
			FileUtils.convertByteToFile(music.getBytes(), rootPath + filePath, fileName);
			scene.setMusicurl(filePath+fileName);
		}	
		
		wechatSceneSqlService.saveSceneInSql(scene);
		int s_id=scene.getS_id();
		logger.info("s_id:" + s_id);

		MultipartFile[] files = new MultipartFile[7];
		int[] labels = new int[7];
		files[0] = file1;
		files[1] = file2;
		files[2] = file3;
		files[3] = file4;
		files[4] = file5;
		files[5] = file6;
		files[6] = file7;
		labels[0] = label1;
		labels[1] = label2;
		labels[2] = label3;
		labels[3] = label4;
		labels[4] = label5;
		labels[5] = label6;
		labels[6] = label7;
		for (int i = 0; i < number; i++) {
			if (!files[i].isEmpty()) {
				//String rootPath = "F:\\aiwac\\h5image\\"; // windows
				String rootPath = "/home/aiwac/apache-tomcat-8.0.47/webapps/website/";  //linux
				String filePath = "resources/sceneimages/"+name+"/";
				//String filePath = number + "\\";
				String fileName = files[i].getOriginalFilename();
				FileUtils.convertByteToFile(files[i].getBytes(), rootPath + filePath, fileName);
				WeChatPicture picture = new WeChatPicture();
				picture.setEmotion(labels[i]);
				picture.setS_id(s_id);
				picture.setSeriel(i);
				picture.setUrl(filePath + fileName);
				wechatPictureSqlService.savePictureInSql(picture);
			}
		}
		return "redirect:/scene/show";
	}

	@RequestMapping("/show")
	public String showScenes(ModelMap map) {
		List<WeChatScene> scenes = wechatSceneSqlService.getSceneInSql();
		map.put("scenes", scenes);
		map.put("size", scenes.size());
		return "sceneControl/showScenes";
	}

	@RequestMapping("/delete")
	public String deleteScenes(@RequestParam("id") int id) {
		WeChatScene scene = new WeChatScene();
		scene.setS_id(id);
		scene.setAvailable(0);
		wechatSceneSqlService.updateSceneInSql(scene);
		return "redirect:/scene/show";
	}

	@RequestMapping("/modify")
	public String modefyScenes(@RequestParam("id") int id) {
		WeChatScene scene = new WeChatScene();
		scene.setS_id(id);
		scene.setAvailable(1);
		wechatSceneSqlService.updateSceneInSql(scene);
		return "redirect:/scene/show";
	}

}