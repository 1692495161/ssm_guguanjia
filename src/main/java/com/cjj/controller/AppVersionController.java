package com.cjj.controller;

import com.cjj.entity.AppVersion;
import com.cjj.entity.Result;
import com.cjj.service.AppVersionService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author cjj
 * @date 2020/7/16
 * @description
 */
@RestController //设置此注解，所有返回值为String的都会转换为axios输出，跳转页面要返回ModelAndView
@RequestMapping("manager/app")//由于tomcat默认会部署一个叫 manager的应用
public class AppVersionController {

    @Autowired
    AppVersionService appVersionService;

    //设置返回值类型ModelAndView一定走视图解析器
    @RequestMapping("index")
    public ModelAndView toIndex(){
        return new ModelAndView("/app/index");//"/WEB-INF/html"+"/app/index"+".html"
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(){
        return new ModelAndView("/app/update");//"/WEB-INF/html"+"/app/index"+".html"
    }

    /*@RequestMapping("selectAll")
    public Result selectAll(){
        List<AppVersion> appVersions = appVersionService.selectAll();
//        int i=5/0;
        return new Result(true,"查询成功",appVersions);
    }*/

    @RequestMapping("selectPage/{pageNum}/{pageSize}")
    public Result selectPage(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize") int pageSize){
        PageInfo<AppVersion> pageInfo = appVersionService.selectPages(pageNum, pageSize);
        return new Result(true,"查询成功",pageInfo);
    }

    @RequestMapping(value = "doUpdate",method = RequestMethod.PUT)
    public Result doUpdate(@RequestBody AppVersion appVersion){
        appVersion.setUpdateDate(new Date());
        int i = appVersionService.updateByPrimaryKeySelective(appVersion);
        return new Result(true,"修改成功",i);
    }

    @RequestMapping(value = "doInsert",method = RequestMethod.POST)
    public Result doInsert(@RequestBody AppVersion appVersion){
        return new Result(true,"增加成功",appVersionService.insertSelective(appVersion));
    }

    /*@RequestMapping("deleteById")
    public ModelAndView deleteById(Long id){
        appVersionService.deleteByPrimaryKey(id);
        return new ModelAndView("/app/index");
    }*/
}
