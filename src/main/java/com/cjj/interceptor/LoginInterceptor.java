package com.cjj.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author cjj
 * @date 2020/7/31
 * @description
 */
/**
 * 拦截器配置流程：
 * 1.编写类继承拦截器HandlerInterceptor
 * 2.实现需要处理拦截的方法
 * 3.将拦截器类放入spring容器管理
 * 4.注册拦截器，添加拦截规则和放行规则
 */
public class LoginInterceptor implements HandlerInterceptor {

    /*
     * 方法执行前拦截，false阻止执行方法
     * 登录拦截：
     * 1.未登录跳转到notlogin.html
     * 2.登录不拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser==null){
            response.sendRedirect(request.getContextPath()+"/notlogin.html");
            return false;
        }
        return true;
    }

//    方法返回后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

//    返回视图到前端时处理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
