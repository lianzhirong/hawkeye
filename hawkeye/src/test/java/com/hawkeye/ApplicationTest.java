package com.hawkeye;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.hawkeye.service.IUserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private IUserService userService;


    @Before
    public void before() {
    }

    @Test
    public void httpGet() {
        String result = HttpUtil.get("http://localhost:9303/user/user/", CharsetUtil.CHARSET_UTF_8);
        System.out.println(result);
    }

    @Test
    public void httpPost() {
        //链式构建请求
        HashMap<String, Object> paramMap = new HashMap();
        paramMap.put("name", "北京");
        String result = HttpRequest.post("http://localhost:9303/user/user/")
                .body(JSONObject.toJSONString(paramMap))
                .contentType("application/json")
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .timeout(20000)//超时，毫秒
                .execute().body();
        System.out.println(result);
    }


    @Test
    public void httpDelete() {
        //链式构建请求
        String result = HttpRequest.delete("http://localhost:9303/user/user/3")
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .timeout(20000)//超时，毫秒
                .execute().body();
        System.out.println(result);
    }

    @Test
    public void httpPut(){
        //链式构建请求
        HashMap<String, Object> paramMap = new HashMap();
        paramMap.put("id",2);
        paramMap.put("name", "上海");
        String result = HttpRequest.put("http://localhost:9303/user/user")
                .body(JSONObject.toJSONString(paramMap))
                .contentType("application/json")
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .timeout(20000)//超时，毫秒
                .execute().body();
        System.out.println(result);
    }


    @After
    public void end() {
    }
}