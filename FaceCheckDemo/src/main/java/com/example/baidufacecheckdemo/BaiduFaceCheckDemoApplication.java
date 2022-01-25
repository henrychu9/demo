package com.example.baidufacecheckdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class BaiduFaceCheckDemoApplication {

    public static void main(String[] args) {
        File file = new File("lib\\opencv_java455.dll");
        String systemProperties = String.valueOf(System.getProperties());
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.load(file.getAbsolutePath());
        SpringApplication.run(BaiduFaceCheckDemoApplication.class, args);
    }

}
