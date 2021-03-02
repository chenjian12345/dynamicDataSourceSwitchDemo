package com.cj.dynamicdatasourcedemo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DataSourceConfiguration {
 
    /**
     *  阳光数据源
     */
    @Bean(name = "yangguangDataSource")
    @Qualifier("yangguangDataSource")
    @ConfigurationProperties(prefix="spring.datasource.yangguang")
    public DataSource yangguangDataSource() {
        return DataSourceBuilder.create().build();
    }
 
    /**
     *  合众数据源
      */
    @Bean(name = "hezhongDataSource")
    @Qualifier("hezhongDataSource")
    @ConfigurationProperties(prefix="spring.datasource.hezhong")
    public DataSource hezhongDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.myMap = new HashMap<>();//保存我们有的数据源，方便后面动态增加
        dynamicDataSource.myMap.put("yangguang",yangguangDataSource());
        dynamicDataSource.myMap.put("hezhong",hezhongDataSource());
//        dynamicDataSource.myMap.put("3",thirdDataSource());
        dynamicDataSource.setTargetDataSources(dynamicDataSource.myMap);//父类的方法
        DynamicDataSourceContextHolder.dataSourceIds.addAll(dynamicDataSource.myMap.keySet());
        dynamicDataSource.setDefaultTargetDataSource(yangguangDataSource());//父类的方法
        return  dynamicDataSource;
    }
}