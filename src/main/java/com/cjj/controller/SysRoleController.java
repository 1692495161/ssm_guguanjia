package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.entity.SysRole;
import com.cjj.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author cjj
 * @date 2020/7/22
 * @description
 */
@RestController
@RequestMapping("manager/role")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping("")
    public ModelAndView toIndex() {
        return new ModelAndView("/role/role");
    }

    @RequestMapping("/toUsers")
    public ModelAndView toUsers() {
        return new ModelAndView("/role/role-user");
    }

    @RequestMapping("/toSelect")
    public ModelAndView toSelect() {
        return new ModelAndView("/role/role-select");
    }

    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate() {
        return new ModelAndView("/role/role-save");
    }

    /*@RequestMapping("/admin/detail")
    public ModelAndView toDetail() {
        return new ModelAndView("/work/work-detail");
    }*/

    @RequestMapping("selectPage/{pageNum}/{pageSize}")
    public Result selectPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize")int pageSize, @RequestBody Map<String,Object> params){
        return new Result(true,"查询成功",sysRoleService.selectPage(pageNum,pageSize,params));
    }

    /*@RequestMapping("/admin/selectDetail")
    public Result selectDetail(long id){
        return new Result(true,"查询成功",sysRoleService.selectDetail(id));
    }*/


    //List<Long>无法自动封装  因为前端传递的数组每个元素，默认处理成int元素，可以组成int[] 或 long[]
    //spring会自动将数组转成Long对象数组
    @RequestMapping("insertBatch")
    public Result insertBatch(long rid,Long[] cids){
        //将数组转换为集合
        List<Long> list = Arrays.asList(cids);
        return new Result(true,"添加成功",sysRoleService.insertBatch(rid,list));
    }

    @RequestMapping("deleteBatch")
    public Result deleteBatch(long rid ,long[] ids){
        return new Result(true,"移除人员成功",sysRoleService.deleteBatch(rid,ids));
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody SysRole sysRole){
        return new Result(true,"修改成功",sysRoleService.updateByPrimaryKeySelective(sysRole));
    }


}
