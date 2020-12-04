package com.bjpowernode.config;

public class MyThreadLocal {
    public static final ThreadLocal<String> threadLocal=new ThreadLocal<>();
    public static String getDataSrouce(){
        return threadLocal.get();
    }
    public static void setDataSource(String dataSource){
        threadLocal.set(dataSource);
    }
}
