package com.cjj.controller;

import com.cjj.entity.Qualification;
import com.cjj.entity.QualificationCondition;
import com.cjj.entity.Result;
import com.cjj.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author cjj
 * @date 2020/7/20
 * @description
 */
@RestController
@RequestMapping("manager/qualification")
public class QualificationController {

    @Autowired
    QualificationService qualificationService;

    @RequestMapping("index")
    public ModelAndView toIndex(){
        return new ModelAndView("/qualification/index");
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(){
        return new ModelAndView("/qualification/update");
    }

    @RequestMapping(value = "selectPage/{pageNum}/{pageSize}",method = RequestMethod.POST)
    public Result getSelectPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize")int pageSize, @RequestBody QualificationCondition condition){
        return new Result(true,"查询成功",qualificationService.selectPages(pageNum,pageSize,condition));
    }

    @RequestMapping(value = "doUpdate",method = RequestMethod.PUT)
    public Result doUpdate(@RequestBody Qualification qualification){
        return new Result(true,"更新成功",qualificationService.updateByPrimaryKeySelective(qualification));
    }

    @RequestMapping("getPath/{uid}")
    public Result getPath(@PathVariable("uid")Long uid){
        return new Result(true,"查询成功",qualificationService.getPath(uid));
    }

}
