package com.example.baidufacecheckdemo.service.impl;

import com.example.baidufacecheckdemo.service.CheckFaceService;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class CheckFaceServiceImpl implements CheckFaceService {
    @Override
    public String checkFace(MultipartFile imageFile) {
        File file = new File("temp\\"+imageFile.getOriginalFilename());
        try {
            InputStream inputStream = imageFile.getInputStream();
            inputStreamToFile(inputStream,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CascadeClassifier faceDetector = new CascadeClassifier("lib\\haarcascade_frontalface_alt2.xml");
        Integer number = face(faceDetector,file.getAbsolutePath());
        return number+"";
    }

    public void inputStreamToFile(InputStream ins, File file){
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file.getAbsolutePath());
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer face(CascadeClassifier faceDetector,String imgPath) {
        /**
         * 读取本地
         */
        Mat image = Imgcodecs.imread(imgPath);
        if (image.empty()) {
            System.out.println("image 内容不存在！");
            return -1;
        }
        // 3 特征匹配
        MatOfRect face = new MatOfRect();
        faceDetector.detectMultiScale(image, face);
        // 4 匹配 Rect 矩阵 数组
        Rect[] rects = face.toArray();
        System.out.println("匹配到 " + rects.length + " 个人脸");
        return rects.length;
    }
}
