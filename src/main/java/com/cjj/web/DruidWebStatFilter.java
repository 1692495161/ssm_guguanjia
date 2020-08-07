package com.cjj.web;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author cjj
 * @date 2020/8/6
 * @description
 */
/**
 * 自定义过滤器  继承 WebStatFilter  实现web监控配置
 */
@WebFilter(urlPatterns = "/*",initParams = {
        @WebInitParam(name = "sessionStatEnable",value = "true"),//session监控
        @WebInitParam(name = "profileEnable",value = "true"),
        @WebInitParam(name = "exclusions",value = "*.js,*.css,*.png") //忽略静态资源的监控
})
public class DruidWebStatFilter extends WebStatFilter {
}
