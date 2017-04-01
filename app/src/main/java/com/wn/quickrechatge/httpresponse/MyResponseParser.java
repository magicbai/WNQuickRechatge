package com.wn.quickrechatge.httpresponse;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by ds on 2016/8/24.
 * 获取responseinfo的信息，在这个类自定义需要获取的数据
 */
public class MyResponseParser implements ResponseParser {
    private Map<String,List<String>> ha;
    @Override
    public void checkResponse(UriRequest request) throws Throwable {
        ha = request.getResponseHeaders();
        int code = request.getResponseCode();
    }

    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        MyResponseInfo responseInfo = new MyResponseInfo();
        responseInfo.setHeader(ha);
        responseInfo.setResult(result);
        return responseInfo;
    }
}
