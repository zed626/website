package com.aiwac.service;

import org.springframework.stereotype.Service;

import com.aiwac.constant.Constant;


@Service
public class FileDetailService {
	
	public String generateFileName(String uuid,int index,String postfix) {
		return uuid + String.valueOf(index) + postfix;
	}
	
	public String generateFilePath(String path) {
		return Constant.PREFIX + path + "/";
	}
}
