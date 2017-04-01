package com.wn.quickrechatge.manager;

import com.wn.quickrechatge.net.HttpApi;
import com.wn.quickrechatge.net.HttpCallback;
import com.wn.quickrechatge.net.ManagerCallback;
import com.wn.quickrechatge.net.MyHttpUtils;
import com.wn.quickrechatge.utils.StringUtils;

import org.xutils.http.RequestParams;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ds on 2017/4/1.
 */
public class LoginManager {
    public void login(String username, String pwd, ManagerCallback callback){
        if (StringUtils.isEmpty(username)){
            callback.onFailure("用户名不能为空");
            return;
        }
        else if (StringUtils.isEmpty(pwd)){
            callback.onFailure("密码不能为空");
            return;
        }
        else {
            String url = HttpApi.getInstance().getUrl(HttpApi.login_url);
            Map<String,Object> data = new HashMap<>();
//            "mobile": "ggg",
//                    "pwd": "123124",
//                    "type ": 1
            RequestParams params = new RequestParams(url);
            params.addBodyParameter("mobile",username);
            params.addBodyParameter("pwd",pwd);
            params.addBodyParameter("type",1+"");
            data.put("mobile",username);
            data.put("pwd",pwd);
            data.put("type",1);
            MyHttpUtils.getInstance().post(url, params, new HttpCallback<String>() {
                @Override
                public void onSuccess(String result, int httpCode) {
                    super.onSuccess(result, httpCode);
                }

                @Override
                public void onFailure(String msg, String errorCode, int httpCode) {
                    super.onFailure(msg, errorCode, httpCode);
                }
            });
        }
    }
}
