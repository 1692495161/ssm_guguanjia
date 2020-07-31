package com.cjj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author cjj
 * @date 2020/7/16
 * @description
 */
@Configuration
//开启事务注解支持
@EnableTransactionManagement
//开启扫描包
@ComponentScan("com.cjj.service")
//读取properties文件
@PropertySource(value = "classpath:path.properties",encoding = "utf-8")
public class SpringServiceConfig {
    //开启事务管理
    @Bean
    public DataSourceTransactionManager getTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
