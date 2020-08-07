package com.cjj.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.cjj.interceptor.SysResourceInterceptor;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.*;
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
@ComponentScan({"com.cjj.service","com.cjj.ascept"})
@EnableAspectJAutoProxy() //开启切面注解支持
//读取properties文件
@PropertySource(value = "classpath:path.properties",encoding = "utf-8")
public class SpringServiceConfig {
    //开启事务管理
    @Bean
    public DataSourceTransactionManager getTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    //该界面有注入，不能new，将其注入spring容器
    @Bean
    public SysResourceInterceptor sysResourceInterceptor(){
        return new SysResourceInterceptor();
    }

    //开启spring监控
    @Bean(name="druidStatInterceptor")//设置druid 的 aop切面类
    public DruidStatInterceptor getDruidStatInterceptor(){
        DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();

        return druidStatInterceptor;
    }


    @Bean//配置spring监控
    public BeanNameAutoProxyCreator getAutoProxyCreator(){
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setProxyTargetClass(true);
        beanNameAutoProxyCreator.setBeanNames(new String[]{"*Mapper","*ServiceImpl"});
        beanNameAutoProxyCreator.setInterceptorNames("druidStatInterceptor");
        return beanNameAutoProxyCreator;
    }
}
