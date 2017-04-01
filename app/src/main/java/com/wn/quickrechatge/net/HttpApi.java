package com.wn.quickrechatge.net;

/**
 * Created by ds on 2017/4/1.
 * url管理
 */
public class HttpApi {
    private static HttpApi instance;
    public static HttpApi getInstance(){
        if (instance == null){
            synchronized (HttpApi.class){
                instance = new HttpApi();
            }
        }
        return instance;
    }
    private static final String baseUrl = "http://112.74.44.166:4840/";
    public String getUrl(String api){
        return baseUrl+api;
    }

    public static final String login_url = "capp/user/login";
}
