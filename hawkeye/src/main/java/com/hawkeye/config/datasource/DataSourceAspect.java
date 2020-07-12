package com.hawkeye.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源配置, 拦截器配置
 **/
@Aspect
@Component
@Slf4j
@Order(1)
public class DataSourceAspect {

    private final String DEFAULT_DATA_SOURCE = "firstAopDataSource";

    @Pointcut("execution(public * com.hawkeye.mapper.*.*(..))")
    public void dataSourcePoint() {
    }

    @Before("dataSourcePoint()")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 执行方法名
        String methodName = methodSignature.getName();
        // 方法参数
        Class[] parameterTypes = methodSignature.getParameterTypes();
        try {
            // 获取方法, 直接getClass获取对象可能为代理对象
            Method method = target.getClass().getInterfaces()[0].getMethod(methodName, parameterTypes);
            // 添加默认数据源
            String dataSource = DEFAULT_DATA_SOURCE;
            if (null != method && method.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource targetDataSource = method.getAnnotation(TargetDataSource.class);
                dataSource = targetDataSource.value();
            }
            // 此处添加线程对应的数据源到上下文
            // 在AbstractRoutingDataSource子类中拿到数据源, 加载后进行配置
            DynamicDataSourceHolder.setDataSource(dataSource);
            log.info("generate data source : " + dataSource);
        } catch (Exception e) {
            log.info("error", e);
        }

    }

    /**
     * 清除数据源, 方法执行完成后, 清除数据源
     */
    @After("dataSourcePoint()")
    public void after(JoinPoint joinPoint) {
        DynamicDataSourceHolder.clear();
    }

}