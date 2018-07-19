package com.aiwac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
*
* @author HC
* @date 2017年12月18日
*
*/

@Controller
public class HomeController {
	@RequestMapping(value = {"/", "home"},method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}
	
	@RequestMapping("/product")
	public String product() {
		return "product";
	}
	
	@RequestMapping("/news")
	public String newsEntry() {
		return "newsEntry";
	}
		
	@RequestMapping("/industryInfo")
	public String industryInfo() {
		return "industryInfo";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	@RequestMapping("/joinUs")
	public String joinUs() {
		return "joinUs";
	}
	
	@RequestMapping("/news/{newsId}")
	public String news(@PathVariable("newsId") String newsId) {
		return newsId;
	}
}