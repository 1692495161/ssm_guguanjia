package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.entity.SysLog;
import com.cjj.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author cjj
 * @date 2020/8/6
 * @description
 */
@RestController
@RequestMapping("manager/syslog")
public class SysLogController {

    @Autowired
    SysLogService logService;

    @RequestMapping("")
    public ModelAndView getModelAndView(){
        return new ModelAndView("/log/log");
    }

    @RequestMapping(value = "selectPage/{pageNum}/{pageSize}",method = RequestMethod.POST)
    public Result selectPage(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize,@RequestBody SysLog sysLog){
        return new Result(true,"查询成功",logService.selectPages(pageNum,pageSize,sysLog));
    }
}
