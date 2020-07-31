package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author cjj
 * @date 2020/7/21
 * @description
 */
@RestController
@RequestMapping("manager/examine")
public class ExamineController {

    @Autowired
    ExamineService examineService;

    @RequestMapping("index")
    public ModelAndView toIndex(){
        return new ModelAndView("/examine/index");
    }

    @RequestMapping(value = "selectPage/{pageNum}/{pageSize}",method = RequestMethod.POST)
    public Result selectPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, @RequestBody Map<String,Object> params){
        return new Result(true,"查询成功",examineService.selectPages(pageNum,pageSize,params));
    }
}
