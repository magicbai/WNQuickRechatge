package com.wn.quickrechatge.httpresponse;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by ds on 2016/8/24.
 * 针对该项目封装实体，包含请求头和所有的结果集，需要更多的response信息在该类定义
 * MyhttpUtils统一返回该类再进行对应的解析
 */
@HttpResponse(parser = MyResponseParser.class)
public class MyResponseInfo {
    String result;
    Map<String,List<String>> header;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<String, List<String>> getHeader() {
        return header;
    }

    public void setHeader(Map<String, List<String>> header) {
        this.header = header;
    }
}
