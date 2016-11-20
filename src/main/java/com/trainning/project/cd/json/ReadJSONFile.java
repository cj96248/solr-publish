package com.trainning.project.cd.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadJSONFile {
    /**
     * 将json文件数据读取到内存
     * @param filepath
     * @return json string
     */
    public static String readFile(String filepath){
        String json = "";
        try {
            File file = new File(filepath);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isInputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader brReader = new BufferedReader(isInputStreamReader);
            String line;
            while((line = brReader.readLine()) != null){
                json += line; 
            }
            brReader.close();
            isInputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    public static void main(String[] args) {
        System.out.println(readFile("src/main/java/com/trainning/project/json/cd.json"));
    }
}
