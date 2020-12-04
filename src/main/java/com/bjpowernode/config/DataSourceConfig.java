package com.bjpowernode.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages={"com.bjpowernode.mapper"},sqlSessionFactoryRef="sqlSessionFactoryBean")
public class DataSourceConfig {
    //主服务器配置信息
    @Value("${master.datasource.driver-class-name}")
    private String masterDriver;
    @Value("${master.datasource.url}")
    private String masterUrl;
    @Value("${master.datasource.username}")
    private String masterUsername;
    @Value("${master.datasource.password}")
    private String masterPassword;
    //从服务器配置信息
    @Value("${slave.datasource.driver-class-name}")
    private String slaveDriver;
    @Value("${slave.datasource.url}")
    private String slaveUrl;
    @Value("${slave.datasource.username}")
    private String slaveUsername;
    @Value("${slave.datasource.password}")
    private String slavePassword;

    @Bean
    public DruidDataSource masterDruidDataSource(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setDriverClassName(masterDriver);
        druidDataSource.setUrl(masterUrl);
        druidDataSource.setUsername(masterUsername);
        druidDataSource.setPassword(masterPassword);
        return druidDataSource;
    }

    @Bean
    public DruidDataSource slaveDruidDataSource(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setDriverClassName(slaveDriver);
        druidDataSource.setUrl(slaveUrl);
        druidDataSource.setUsername(slaveUsername);
        druidDataSource.setPassword(slavePassword);
        return druidDataSource;
    }

    @Bean
    public MyDataSource myDataSource(DruidDataSource masterDruidDataSource,DruidDataSource slaveDruidDataSource){
        MyDataSource myDataSource=new MyDataSource();
        myDataSource.setDefaultTargetDataSource(masterDruidDataSource);
        Map map=new HashMap();
        map.put("master",masterDruidDataSource);
        map.put("slave",slaveDruidDataSource);
        myDataSource.setTargetDataSources(map);
        return myDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(MyDataSource myDataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myDataSource);
        return sqlSessionFactoryBean;
    }
}
