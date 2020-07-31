package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.entity.SysOffice;
import com.cjj.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cjj
 * @date 2020/7/21
 * @description
 */
@RestController
@RequestMapping("manager/office")
public class SysOfficeController {
    @Autowired
    SysOfficeService sysOfficeService;

    @RequestMapping("select")
    public Result select(){
        SysOffice sysOffice = new SysOffice();
        sysOffice.setDelFlag("0");
        List<SysOffice> select = sysOfficeService.select(sysOffice);
        return new Result(true,"查询成功",select);
    }
}
