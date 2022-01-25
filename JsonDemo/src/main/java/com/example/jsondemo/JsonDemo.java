package com.example.jsondemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.jsondemo.utils.JsonTest;

import java.io.File;
import java.io.IOException;

public class JsonDemo {

    public static void main(String[] args) throws IOException {
        String path = "app\\Sample.json";
        String s = JsonTest.readJsonFile(path);
        JSONObject jobj = JSON.parseObject(s);
        JSONObject items = jobj.getJSONObject("items");//构建JSONArray数组
        JSONArray item = items.getJSONArray("item");
        JsonTest.writeTableFile(item);
    }
}
