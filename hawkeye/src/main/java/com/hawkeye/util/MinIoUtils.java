package com.hawkeye.util;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Configuration
public class MinIoUtils {

    @Value("${minio.host}")
    private   String host;
    @Value("${minio.accessKey}")
    private  String accessKey;
    @Value("${minio.secretKey}")
    private  String secretKey;

    private static  MinioClient instance;

    @PostConstruct
    public void MinIoUtils(){
        instance = new MinioClient(host, accessKey, secretKey);
    }

    /**
     * 判断 bucket是否存在
     *
     * @param bucketName
     * @return
     */
    public static  boolean bucketExists(String bucketName) {
        try {
            return instance.bucketExists(bucketName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建 bucket
     *
     * @param bucketName
     */
    public static void makeBucket(String bucketName) {
        try {
            boolean isExist = instance.bucketExists(bucketName);
            if (!isExist) {
                instance.makeBucket(bucketName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     *
     * @param bucketName
     * @param objectName
     * @param filename
     */
    public static void putObject(String bucketName, String objectName, String filename) {
        try {
            instance.putObject(bucketName, objectName, filename, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     *
     * @param bucketName
     * @param objectName
     * @param stream
     */
    public static void putObject(String bucketName, String objectName, InputStream stream) {
        try {
            instance.putObject(bucketName, objectName, stream, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param bucketName
     * @param objectName
     */
    public static void removeObject(String bucketName, String objectName) {
        try {
            instance.removeObject(bucketName, objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

