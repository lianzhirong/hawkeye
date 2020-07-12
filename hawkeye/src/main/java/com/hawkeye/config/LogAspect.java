package com.hawkeye.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Configuration
@Slf4j
public class LogAspect {
    @Pointcut("execution(* com.hawkeye.controller.*Controller.*(..))")
    public void excudeService() {
    }
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, Object> headerMap = new HashMap(10);
        do {
            String header = headerNames.nextElement();
            headerMap.put(header, request.getHeader(header));
        } while (headerNames.hasMoreElements());

        String token = request.getHeader("token");
        long start=System.currentTimeMillis ();
        String method =request.getMethod();
        String requestParam =  JSONObject.toJSONString(request.getParameterMap());
        if("POST".equals(method)){
            for (Object o : joinPoint.getArgs()) {
                if (o instanceof HttpServletRequest || o instanceof HttpServletResponse) {
                    continue;
                }
                requestParam = JSONObject.toJSONString(o);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("***************************start "+sdf.format(start)+" *************************************************");
        String uri = request.getRequestURI();
        log.info("\n" +
                        "请求地址  >>>  {}\n" +
                        "请求方法  >>>  {}\n" +
                        "请求参数  >>>  {}\n" +
                        "请求来源  >>>  {}\n" +
                        "内容类型  >>>  {}\n" +
                        "用户令牌  >>>  {}\n" +
                        "请求头部  >>>  {}\n",
                request.getRequestURI(),
                method,
                requestParam,
                request.getRemoteAddr(),
                request.getContentType(),
                token,
                JSON.toJSONString(headerMap));
        log.info("请求开始, URI: {}, method: {}, params: {}", uri,  method, requestParam);
        Object result = joinPoint.proceed();
        long end=System.currentTimeMillis ();
        log.info("\n请求结束"+uri+" "+sdf.format(end)+"耗时 "+(end-start)+"ms"+"\n"+JSON.toJSONString(result,true));
        log.info("请求结束，controller的返回值是 " + JSON.toJSON (result));
        log.info("***************************end   "+sdf.format(end)+"耗时 "+(end-start)+"ms");
        return result;
    }

}