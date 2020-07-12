package com.hawkeye.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
 
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
 
/**
 * 配置数据源
 **/
@Configuration
public class DataSourceConfig {
 
    /**
     * First数据源
     * @return
     */
    @Bean(name = "firstAopDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }
 
    /**
     * Second数据源
     * @return
     */
    @Bean(name = "secondAopDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }
 
    /**
     * 获取动态数据源
     * @return
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 设置默认数据源为first数据源
        dynamicDataSource.setDefaultTargetDataSource(firstDataSource());
        // 配置多数据源, 
        // 添加数据源标识和DataSource引用到目标源映射
        Map<Object, Object> dataSourceMap = new HashMap();
        dataSourceMap.put("firstAopDataSource", firstDataSource());
        dataSourceMap.put("secondAopDataSource", secondDataSource());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }
 
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
 
}
