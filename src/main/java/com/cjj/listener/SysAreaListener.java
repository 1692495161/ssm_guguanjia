package com.cjj.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.cjj.entity.SysArea;
import com.cjj.mapper.SysAreaMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjj
 * @date 2020/7/30
 * @description
 */
/*
 * 分析事件监听器：
 * 实现了读监听接口，需要实现读取一行自动调用的方法invoke和读取文件完成后自动调用的方法doAfterAllAnalysed
 * easyexcel官方规范：需要使用spring管理的bean通过构造器传入，不要将SysAreaListener给spring容器管理
 * */
public class SysAreaListener extends AnalysisEventListener<SysArea> {

    SysAreaMapper  mapper;

    public SysAreaListener() {
    }

    public SysAreaListener(SysAreaMapper mapper) {
        this.mapper = mapper;
    }

    List<SysArea> list=new ArrayList<>();

    /*
     * 每读取一行excel自动执行的方法
     * sysArea是解析一行excel记录后自动封装的java对象
     * analysisContext是easyexcel的上下文对象
     * */
    @Override
    public void invoke(SysArea area, AnalysisContext analysisContext) {
        list.add(area);
        //插入10条数据
        if (list.size()>10){
            //加入数据库
            mapper.insertArea(list);
            list.clear();
        }
    }

    /*
     * 解析完成后的处理方法
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //将剩余的数据加入数据库
        if (list.size()>0){
            mapper.insertArea(list);
        }
    }
}
