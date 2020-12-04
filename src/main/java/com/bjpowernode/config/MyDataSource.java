package com.bjpowernode.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 自定义动态数据源类用于在程序运行中切换数据源的并继承动态数据源的抽象父类
 */
public class MyDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return MyThreadLocal.getDataSrouce();
    }
}
