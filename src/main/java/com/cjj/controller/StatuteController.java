package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.entity.Statute;
import com.cjj.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author cjj
 * @date 2020/7/23
 * @description
 */
@RestController
@RequestMapping("manager/statute")
public class StatuteController {

    @Autowired
    StatuteService statuteService;

    @RequestMapping("index")
    public ModelAndView toIndex() {
        return new ModelAndView("/statute/index");
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate() {
        return new ModelAndView("/statute/update");
    }

    @RequestMapping("selectPage/{pageNum}/{pageSize}")
    public Result selectPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, Integer type) {
        Statute statute = new Statute();
        statute.setDelFlag("0");
        statute.setType(type);
        return new Result(true,"查询成功",statuteService.selectPages(pageNum,pageSize,statute));
    }

    @RequestMapping(value = "doUpdate",method = RequestMethod.PUT)
    public Result doUpdate(@RequestBody Statute statute) {
        statute.setUpdateDate(new Date());
        return new Result(true,"修改成功",statuteService.updateByPrimaryKeySelective(statute));
    }

    @RequestMapping(value = "toInsert",method = RequestMethod.POST)
    public Result toInsert(@RequestBody Statute statute) {
        statute.setCreateDate(new Date());
        statute.setUpdateDate(new Date());
        statute.setDelFlag("0");
        return new Result(true,"增加成功",statuteService.insertSelective(statute));
    }
}
