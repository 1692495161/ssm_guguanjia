package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cjj
 * @date 2020/8/3
 * @description
 */
@RestController
@RequestMapping("/manager/sysuser")
public class SysUserController {
    @Autowired
    SysUserService userService;

    @RequestMapping("/selectByRid")
    public Result selectByRid(long rid){
        return new Result(true,"查询成功",userService.selectByRid(rid));
    }

    @RequestMapping("/selectNoRole")
    public Result selectNoRole(long oid,long rid){
        return new Result(true,"查询成功",userService.selectNoRole(oid,rid));
    }
}
