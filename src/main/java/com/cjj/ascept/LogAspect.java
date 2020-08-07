package com.cjj.ascept;

import com.cjj.entity.SysLog;
import com.cjj.entity.SysUser;
import com.cjj.service.SysLogService;
import com.cjj.utils.IPUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author cjj
 * @date 2020/8/6
 * @description
 */
@Component
@Aspect  //声明是一个切面类
public class LogAspect {

    @Autowired
    SysLogService logService;
    @Autowired
    HttpServletRequest request;

    /*public LogAspect() {
        System.out.println("LogAspect.................");
    }*/

    //切入点表达式，该路径下所有以Impl结尾的类 within(com.cjj.service.impl.*Impl)
    @Pointcut("within(com.cjj.service.impl.*Impl)")
    public void pointcut(){}

    //正常日志通知（返回通知）
    @AfterReturning(pointcut = "pointcut()", returning = "obj")
    public Object afterReturning(JoinPoint joinPoint,Object obj) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        if (!className.equals("SysLogServiceImpl")){
            insertLog(joinPoint,null);
        }
        return obj;
    }


    //异常日志
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Exception e) {
        insertLog(joinPoint,e);
    }

    public void insertLog(JoinPoint joinPoint, Exception e) {
        SysLog sysLog = new SysLog();
        sysLog.setType(e == null ? "1" : "2");
        if (request != null) {
            SysUser loginUser = (SysUser) request.getSession().getAttribute("loginUser");
            sysLog.setCreateBy(loginUser == null ? "" : loginUser.getName());
            sysLog.setCreateDate(new Date());
            sysLog.setRemoteAddr(IPUtils.getClientAddress(request));
            sysLog.setUserAgent(request.getHeader("User-Agent"));
            sysLog.setRequestUri(request.getRequestURI());
            sysLog.setMethod(request.getMethod());
        }
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                sb.append("[参数")
                  .append((i + 1))
                  .append(",类型：")
                  .append(args[i].getClass().getSimpleName())
                  .append(",值：")
                  .append(args[i])
                  .append("]");
            }
            sysLog.setParams(sb.toString());
        }
        sysLog.setException(e == null ? "" : e.toString());
        logService.insertSelective(sysLog);
    }
}
