package com.cj.dynamicdatasourcedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
// 自定义数据源一定要排除SpringBoot自动配置数据源，不然会出现循环引用的问题，The dependencies of some of the beans in the application context form a cycle
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DynamicDataSourceDemoApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(DynamicDataSourceDemoApplication.class, args);
    }

}
