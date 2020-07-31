package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author cjj
 * @date 2020/7/22
 * @description
 */
@RestController
@RequestMapping("manager")
public class WorkOrderController {

    @Autowired
    WorkOrderService workOrderService;

    @RequestMapping("/admin/work")
    public ModelAndView toIndex() {
        return new ModelAndView("/work/admin/index");
    }

    @RequestMapping("/admin/detail")
    public ModelAndView toDetail() {
        return new ModelAndView("/work/work-detail");
    }

    @RequestMapping("selectPage/{pageNum}/{pageSize}")
    public Result selectPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize")int pageSize, @RequestBody Map<String,Object> params){
        return new Result(true,"查询成功",workOrderService.selectPage(pageNum,pageSize,params));
    }

    @RequestMapping("/admin/selectDetail")
    public Result selectDetail(long id){
        return new Result(true,"查询成功",workOrderService.selectDetail(id));
    }


}
