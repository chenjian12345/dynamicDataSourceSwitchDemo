package com.cj.dynamicdatasourcedemo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @Author Cheng ZhiHua
 * @Date 2019-11-05 16:01
 * @Description 核心方法 ：继承AbstractRoutingDataSource 类，将数据源交给AbstractRoutingDataSource进行注入使用
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
 
    public Map<Object,Object> myMap = null;
 
    @Override
    protected Object determineCurrentLookupKey() {
        /*
         * DynamicDataSourceContextHolder代码中使用setDataSourceType
         * 设置当前的数据源，在路由类中使用getDataSourceType进行获取，
         *  交给AbstractRoutingDataSource进行注入使用。
         */
//        log.info("数据源为: {}",DynamicDataSourceContextHolder.getDataSourceType());
        return DynamicDataSourceContextHolder.getDataSourceType();
 
    }
 
}