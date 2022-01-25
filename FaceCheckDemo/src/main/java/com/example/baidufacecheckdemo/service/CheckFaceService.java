package com.example.baidufacecheckdemo.service;

import org.springframework.web.multipart.MultipartFile;

public interface CheckFaceService {

    public String checkFace(MultipartFile imgFile);
}
