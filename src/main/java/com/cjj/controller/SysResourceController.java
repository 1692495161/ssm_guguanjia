package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.entity.SysResource;
import com.cjj.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cjj
 * @date 2020/8/4
 * @description
 */
@RestController
@RequestMapping("manager/menu")
public class SysResourceController {

    @Autowired
    SysResourceService resourceService;

    @RequestMapping("select")
    public Result select(){
        SysResource sysResource = new SysResource();
        sysResource.setDelFlag("0");
        return new Result(true,"查询成功",resourceService.select(sysResource));
    }

    @RequestMapping("selectByRid")
    public Result selectByRid(long rid){
        return new Result(true,"查询成功",resourceService.selectByRid(rid));
    }
}
