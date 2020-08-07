package com.cjj.config;

import com.cjj.interceptor.LoginInterceptor;
import com.cjj.interceptor.SysResourceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author cjj
 * @date 2020/7/16
 * @description
 */
@Configuration
//开启支持Mvc注解支持
@EnableWebMvc
@ComponentScan("com.cjj.controller")
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();  //静态资源放行
    }

    @Bean
    public InternalResourceViewResolver getViewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/html",".html");
    }

    /*
     * CommonsMultipartResolver:以commons的fileupload组件实现的文件上传解析器对象
     * 解析Request中的文件流封装成MultipartFile对象
     */
    @Bean("multipartResolver")
    public CommonsMultipartResolver getMultipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
    }

    //将该拦截器放入spring容器，注入进来
    @Autowired
    SysResourceInterceptor resourceInterceptor;

    //注册拦截器，配置拦截和放行规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        //登录拦截注册处理对象
        InterceptorRegistration interceptor = registry.addInterceptor(loginInterceptor);

        //设置拦截规则
        interceptor.addPathPatterns("/**");//拦截所有
        //设置放行规则
        interceptor.excludePathPatterns("/main/*","/login/toLogin","/login/loginOut");

        InterceptorRegistration interceptor1 = registry.addInterceptor(resourceInterceptor);
        interceptor1.addPathPatterns("/manager/**");
    }
}
