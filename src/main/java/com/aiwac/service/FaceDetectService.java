package com.aiwac.service;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencv.core.Core;  
import org.opencv.core.Mat;  
import org.opencv.core.MatOfRect;  
import org.opencv.core.Point;  
import org.opencv.core.Rect;  
import org.opencv.core.Scalar;  
import org.opencv.imgcodecs.Imgcodecs;  
import org.opencv.imgproc.Imgproc;  
import org.opencv.objdetect.CascadeClassifier;  

/** 
 *   
 * OpenCV3.4 FaceDetect 
 * 
 */  


public class FaceDetectService
{  
    /** 
     * 识别指定图片上的所有人脸 
     * @param inImg 输入原图 
     * @param outImg 输出图片，在原图的基础上添加"识别框"  
     */  
	private static final Logger logger = LogManager.getLogger(FaceDetectService.class);
	static{
    	logger.info("load native lib");  
    	System.load("/usr/local/share/OpenCV/java/libopencv_java330.so");  
    	//System.load("C:\\Users\\Admin\\Downloads\\opencv\\build\\java\\x64\\opencv_java330.dll"); 
	}
	

	
    public static void detect(String inImg,String outImg){  
        //copy opencv_java340.dll under the jdk1.8/bin jdk1.8/jre/bin dir.  
        //将opencv_java340.dll拷贝到jdk的/jre/bin目录下面  
        // TODO Auto-generated method stub  
        File f = new File(inImg);  
          
        //原图片不存在直接退出  
        if(!f.exists()){  
            System.out.println("\n Image File Not Found!");  
            return;  
        }  
        //获取原文件路径,让输出文件与原文件保存在同一路径  
        String filePath = f.getAbsolutePath().substring(0, f.getAbsolutePath().indexOf(File.separator)+1);  
       //加载OPENCV3.4本地库,必须先加载  
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  
          
        System.out.println("\nRunning FaceDetector");  
        CascadeClassifier faceDetector = new CascadeClassifier();  
        /** 
         * 分类器，路径根据实际情况调整 
         * haarcascade_frontalface_default  默认 
         * haarcascade_frontalface_alt 识别性能要好些 
         */  
        faceDetector.load(  
                "C:\\Users\\Admin\\Downloads\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");  
        //读取图像  
        Mat image = Imgcodecs.imread(inImg);  
        //用于保存监测到的人脸  
        MatOfRect faceDetections = new MatOfRect();  
        //开始监测  
        faceDetector.detectMultiScale(image, faceDetections);  
          
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));  
        int i=0;  
          
        for (Rect rect : faceDetections.toArray())  
        {  
            //循环所有监测到的人脸  
            Point x = new Point(rect.x, rect.y);  
            Point y = new Point(rect.x + rect.width, rect.y + rect.height);  
            //在image图片上画框，x，y可确定框的位置和大小，new Scalar(0, 255, 0)是框的颜色，自行调整  
            Imgproc.rectangle(image, x,y, new Scalar(0, 255, 0));  
              
            //保存监测的人脸小图片  
            Rect r = new Rect(x, y);  
            System.out.println(r.height+":"+r.width);  
            Mat areaM = new Mat(image, r);  
            //保存监测的人脸小图片到tmp+序号的jpg文件  
            String tmpFilePath = filePath+"tmp"+(i++)+".jpg";  
            Imgcodecs.imwrite(tmpFilePath, areaM);  
              
        }  
        //保存画了方框的图片  
        String filename = outImg;  
        Imgcodecs.imwrite(filename, image);  
        //销毁  
        image.release();  
    }
    
    public static int detectFace(String inImg){  
        logger.info("Running FaceDetector");  
        Mat image = Imgcodecs.imread(inImg);
        CascadeClassifier faceDetector = new CascadeClassifier();  
        /** 
         * 分类器，路径根据实际情况调整 
         * haarcascade_frontalface_default  默认 
         * haarcascade_frontalface_alt 识别性能要好些 
         */  
        faceDetector.load(  
                "/root/OpenCV/haarcascades/haarcascade_frontalface_alt.xml"); 
        //faceDetector.load(  
        //        "C:\\Users\\Admin\\Downloads\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml"); 
        //读取图像  
        //Mat image = Imgcodecs.imread(inImg);
        //用于保存监测到的人脸  
        MatOfRect faceDetections = new MatOfRect();  
        //开始监测  
        faceDetector.detectMultiScale(image, faceDetections);  
          
        logger.info(String.format("Detected %s faces", faceDetections.toArray().length));  
        return faceDetections.toArray().length;
    }  
}  

