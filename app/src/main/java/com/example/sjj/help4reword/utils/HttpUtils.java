package com.example.sjj.help4reword.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 从服务器请求获取到JSON数据格式的字符串
 * Created by sjj on 2018/4/14.
 */

public class HttpUtils {

    public HttpUtils(){

    }

    private static String getJsonContent(String url_path,String Method){
        try{
            URL url = new URL(url_path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setRequestMethod(Method);
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if(code == 200){
                return ChangeInputStream(connection.getInputStream());
            }
        }catch (Exception e){

        }
        return "";
    }

    private static String ChangeInputStream(InputStream inputStream){
        String jsonString = "";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int length = 0;
        byte[] data = new byte[1024];
        try{
            while(-1 != (length = inputStream.read(data))){
                outputStream.write(data, 0, length);
            }
            jsonString = new String(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return jsonString;
    }

}
