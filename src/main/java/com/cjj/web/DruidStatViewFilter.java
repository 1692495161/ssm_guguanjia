package com.cjj.web;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author cjj
 * @date 2020/8/6
 * @description
 */
@WebServlet(urlPatterns = "/druid/*",initParams = {
        @WebInitParam(name="loginUsername",value = "druid"),
        @WebInitParam(name="loginPassword",value = "123")
})
public class DruidStatViewFilter extends StatViewServlet {
}
