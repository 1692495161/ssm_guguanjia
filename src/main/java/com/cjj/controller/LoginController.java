package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.entity.SysResource;
import com.cjj.entity.SysUser;
import com.cjj.service.SysResourceService;
import com.cjj.service.SysUserService;
import com.cjj.utils.EncryptUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author cjj
 * @date 2020/7/31
 * @description
 */
@Controller
@RequestMapping("login")
public class LoginController {


    @Autowired
    SysUserService userService;

    @Autowired
    SysResourceService resourceService;

    @RequestMapping("toIndex")
    public ModelAndView toIndex(){
        return new ModelAndView("/index");
    }

    @RequestMapping("toLogin")
    @ResponseBody
    public Result toLogin(String username, String password, String code, @SessionAttribute("checkCode")String checkCode, HttpSession session){
        if (code.equals(checkCode)){
            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            sysUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password)+username));
            SysUser loginUser = userService.selectOne(sysUser);
            if (loginUser!=null){
                //根据用户的登录信息的id获取该用户的权限
                List<SysResource> resources = resourceService.selectByUid(loginUser.getId());
                loginUser.setResources(resources);

                session.setAttribute("loginUser",loginUser);
                session.setAttribute("resources",resources);
                loginUser.setPassword(null); //将密码请出后传给前端，防止session显示密码
                return new Result(true,"登陆成功",loginUser);
            }else {
                return new Result(false,"用户名或密码错误",null);
            }
        }
        return new Result(false,"验证码错误",null);
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        //清空session存储
        session.invalidate();
        //跳转登录界面
        return "redirect:/login.html";
    }

    /*@RequestMapping("selectByUid")
    public Result selectByUid(long uid){
        return new Result(true,"查询成功",resourceService.selectByUid(uid));
    }*/
}
