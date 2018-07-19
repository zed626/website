package com.aiwac.tool;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtils {
	private static final Logger logger = LogManager.getLogger(FileUtils.class);
	public static void convertByteToFile(byte[] fileContent,String filePath,String fileName) {
		logger.info("convertByteToFile: " + filePath + fileName);
		File fp = new File(filePath);  
        if (!fp.exists()) {  
            fp.mkdirs();
        }  
		try {
			OutputStream out = new FileOutputStream(filePath+fileName);
	        InputStream is = new ByteArrayInputStream(fileContent);
	        byte[] buff = new byte[1024];
	        int len = 0;
	        while((len=is.read(buff)) != -1){
	            out.write(buff, 0, len);
	        }
	        is.close();
	        out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] readFileAndConvertToByte(String filePath) throws IOException{
		logger.info("readFileAndConvertToByte: " + filePath);
		File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }
 
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
	}
	
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			logger.info("file not exist! Path: " + filePath);
			return false;
		}
		if(file.isFile() && file.delete()) {
			logger.info("delete file: " + filePath);
			return true;
		}
		logger.info("delete fail! " + filePath);
		return false;
	}

}
