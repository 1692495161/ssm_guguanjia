package com.cjj.controller;

import com.cjj.entity.Result;
import com.cjj.entity.SysArea;
import com.cjj.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author cjj
 * @date 2020/7/29
 * @description
 */
@RestController
@RequestMapping("manager/area")
public class SysAreaController {

    @Autowired
    AreaService areaService;

    @RequestMapping("")
    public ModelAndView toIndex() {
        return new ModelAndView("/area/index");
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate() {
        return new ModelAndView("/area/save");
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        return new ModelAndView("/area/add");
    }

    @RequestMapping("toModules")
    public ModelAndView toModules() {
        return new ModelAndView("/modules/font-awesome");
    }

    @RequestMapping("toSelect")
    public ModelAndView toSelect() {
        return new ModelAndView("/area/select");
    }

    @RequestMapping("select")
    public Result select() {
        SysArea sysArea = new SysArea();
        sysArea.setDelFlag("0");
        return new Result(true, "查询成功", areaService.select(sysArea));
    }

    @RequestMapping("selectById/{pageNum}/{pageSize}")
    public Result selectById(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, long id) {
        return new Result(true, "查询成功", areaService.selectById(pageNum, pageSize, id));
    }

    @RequestMapping("selectByName/{pageNum}/{pageSize}")
    public Result selectByName(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, String name) {
        return new Result(true, "查询成功", areaService.selectByName(pageNum, pageSize, name));
    }

    @RequestMapping(value = "toUpdate",method = RequestMethod.PUT)
    public Result toUpdate(@RequestBody SysArea area) {
        return new Result(true, "更新成功",areaService.updateByPrimaryKeySelective(area));
    }

    @RequestMapping(value = "toDelete",method = RequestMethod.PUT)
    public Result toDelete(@RequestBody SysArea area) {
        return new Result(true, "删除成功",areaService.toDelete(area));
    }


    /* @ExcelProperty("name")指定生产的表头的字段名
     * @ExcelIgnore  忽略生成到表头
     * @DateTimeFormat("yyyy-MM-dd")  :格式化日期
     * @NumberFormat("0.00")   :  设置显示数字格式
     */
    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        //1.设置响应头信息,才可以下载
        //new String("表1.xls".getBytes(),"iso-8859-1")  解决客户端显示中文文件名乱码问题
        response.setHeader("Content-Disposition","attachment;filename="+new String("表1.xls".getBytes(),"iso-8859-1"));
        ServletOutputStream outputStream = response.getOutputStream();
        areaService.download(outputStream);
    }

    //必须实现文件上传解析器配置
    @RequestMapping("upload")
    public Result upload(MultipartFile file) throws IOException {
        areaService.upload(file.getInputStream());
        return new Result(true,"导入成功",file.getOriginalFilename());
    }
}
