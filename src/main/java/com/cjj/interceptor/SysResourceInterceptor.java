package com.cjj.interceptor;

import com.cjj.entity.SysResource;
import com.cjj.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author cjj
 * @date 2020/8/5
 * @description
 */
/**
 * 授权拦截：
 * 1.获取系统所有需要授权资源(type为1的按钮级别、url为''和公共资源不授权，直接可以访问)
 * 2.获取用户请求,判断是否需要授权,不需要授权则直接放行
 * 3.需要授权的资源，再从用户已授权资源中查找是否有授权 有则放行，无则阻止访问  跳转到notauth.html
 */
public class SysResourceInterceptor implements HandlerInterceptor {

    @Autowired
    SysResourceService resourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取需要授权的所有系统资源
        List<SysResource> sysResources = resourceService.selectResources();

        //获取请求路径
        String uri = request.getRequestURI(); //  /guguanjia（request.getContextPath()）/请求路径
        uri=uri.replace(request.getContextPath()+"/","");

        //授权标记
        boolean flag=false;
        //如果请求路径在需要授权的资源中，则需要判断该用户是否具有该权限
        for (SysResource sysResource : sysResources) {
            if (sysResource.getUrl().equals(uri)){
                //该请求路径需要进行授权
                flag=true;
                break;
            }
        }

        if (flag){
            //获取已授权的资源
            List<SysResource> resources = (List<SysResource>) request.getSession().getAttribute("resources");
            for (SysResource resource : resources) {
                if (resource.getUrl().equals(uri)){
                    return true;
                }
            }
            request.getRequestDispatcher("/notauth.html").forward(request,response);
            return false;
        }
        return true;
    }
}
