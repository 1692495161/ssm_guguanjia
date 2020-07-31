package com.cjj.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.cjj.utils.MapWrapperFactory;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author cjj
 * @date 2020/7/16
 * @description
 */
@Configuration
//@MapperScan("com.cjj.mapper")
//转为tk-mapper的包
@MapperScan("com.cjj.mapper")
@Import({SpringServiceConfig.class,SpringRedisConfig.class,SpringCacheConfig.class})
public class SpringMyBatisConfig {

    @Bean
    public DruidDataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        InputStream is = SpringMyBatisConfig.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
            dataSource.configFromPropety(prop);
            return dataSource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public SqlSessionFactoryBean getFactoryBean(DruidDataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //将mybatis的包转换成tk-mapper的
        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        //驼峰命名格式
        configuration.setMapUnderscoreToCamelCase(true);
        //设置对map的value为null的数据key保持显示
        configuration.setCallSettersOnNulls(true);
        //声明工具类工厂
        configuration.setObjectWrapperFactory(new MapWrapperFactory());
        //设置mapper的全局配置
        factoryBean.setConfiguration(configuration);

        //配置分页插件
        PageInterceptor interceptor = new PageInterceptor();
        //设置默认的分页处理组件对象  PageHelper,由于Properties属性不会主动配置，需要手动
        interceptor.setProperties(new Properties());
        //设置分页插件
        factoryBean.setPlugins(interceptor);
        return factoryBean;
    }
}
