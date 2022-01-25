package com.example.baidufacecheckdemo.controller;

import com.example.baidufacecheckdemo.service.CheckFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("check")
public class CheckFaceController {

    @Autowired
    private CheckFaceService checkFaceService;
    @RequestMapping(value = "checkFace",headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    public String checkFace(@RequestParam("imgFile") MultipartFile imgFile){
        return checkFaceService.checkFace(imgFile);
    }
}
