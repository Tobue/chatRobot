package com.chatRobot.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by Administrator on 2017/12/8.
 */
public class LoginUtil {

    public static String getToken(String username,String password) {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();

            //使用配置文件
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(new File(LoginUtil.class.getResource("/").getPath()+"jdbc.properties"));
            properties.load(fis);
            String url = properties.getProperty("url.loginToken");
            HttpPost httpPost = new HttpPost(url);

            //json方式
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("userName", username);
            jsonParam.put("userPassword", password);
            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            String respContent = null;
            HttpResponse resp = client.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == 200) {
                respContent = EntityUtils.toString(resp.getEntity(), "UTF-8");
            }

            JSONObject jsonObject = JSONObject.parseObject(respContent);
            String token = jsonObject.getString("token");
            return  token;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}




