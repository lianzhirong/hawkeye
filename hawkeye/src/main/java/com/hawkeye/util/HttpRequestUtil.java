package com.hawkeye.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class HttpRequestUtil {

    public String httpGet(String url) {
        String result = HttpUtil.get(url, CharsetUtil.CHARSET_UTF_8);
        return result;
    }

    public String httpPost(String url, Map<String, Object> paramMap) {
        String result = HttpRequest.post(url)
                .body(JSONObject.toJSONString(paramMap))
                .contentType("application/json")
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .timeout(20000)//超时，毫秒
                .execute().body();
        return result;
    }

    public String httpDelete(String url) {
        //链式构建请求
        String result = HttpRequest.delete(url)
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .timeout(20000)//超时，毫秒
                .execute().body();
        return result;
    }

    public String httpPut(String url, Map<String, Object> paramMap) {
        String result = HttpRequest.put(url)
                .body(JSONObject.toJSONString(paramMap))
                .contentType("application/json")
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .timeout(20000)//超时，毫秒
                .execute().body();
        return result;
    }

}
