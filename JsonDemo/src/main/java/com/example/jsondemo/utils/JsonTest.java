package com.example.jsondemo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class JsonTest {
    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeTableFile(JSONArray items) throws IOException {
        if (items == null || items.size() == 0) {
            return;
        }
        String path = "app\\result\\sortbyid.table";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        Writer writer = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
        writer.write("<table border>\n\r");
        writer.write("<thead>\n\r");
        writer.write("<tr><th>Id</th><th>Type</th><th>Name</th><th>Batter</th><th>Topping</th></tr>\n\r");
        writer.write("<tbody>\n\r");
        for (int i = 0; i < items.size(); i++) {
            JSONObject item = items.getJSONObject(i);
            if (item != null) {
                JSONObject batters = item.getJSONObject("batters");
                JSONArray batterArray = batters.getJSONArray("batter");
                JSONArray toppingArray = item.getJSONArray("topping");
                for (int j = 0; j < batterArray.size(); j++) {
                    JSONObject batter = batterArray.getJSONObject(j);
                    for (int k = 0; k < toppingArray.size(); k++) {
                        JSONObject topping = toppingArray.getJSONObject(k);
                        writer.write("<tr><th>" + item.getString("id") + "</th><th>" + item.getString("type") + "</th><th>" + item.getString("name") + "</th><th>" + batter.getString("type") + "</th><th>" + topping.getString("type") + "</th></tr>\n\r");
                    }
                }

            }
        }
        writer.write("</tbody>\n\r");
        writer.write("</thead>\n\r");
        writer.write("</table>");
        writer.close();
    }

}
