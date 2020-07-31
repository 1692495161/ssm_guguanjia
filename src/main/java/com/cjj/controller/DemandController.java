package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author cjj
 * @date 2020/7/18
 * @description
 */
@RestController
@RequestMapping("manager/demand")
public class DemandController {
    @Autowired
    DemandService demandService;

    @RequestMapping("index")
    public ModelAndView toIndex(){
        return new ModelAndView("/demand/index");
    }

    @RequestMapping("detail")
    public ModelAndView toDetail(){
        return new ModelAndView("/demand/detail");
    }

    @RequestMapping("selectPage/{pageNum}/{PageSize}")
    public Result selectPage(@PathVariable("pageNum") int pageNum,@PathVariable("PageSize") int PageSize){
        return new Result(true,"查询成功",demandService.selectPages(pageNum,PageSize));
    }
}
