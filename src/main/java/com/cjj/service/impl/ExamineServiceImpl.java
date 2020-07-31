package com.cjj.service.impl;

import com.cjj.entity.Examine;
import com.cjj.entity.Qualification;
import com.cjj.entity.QualificationCondition;
import com.cjj.entity.SysUser;
import com.cjj.mapper.ExamineMapper;
import com.cjj.mapper.QualificationMapper;
import com.cjj.mapper.SysUserMapper;
import com.cjj.service.ExamineService;
import com.cjj.service.QualificationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cjj
 * @date 2020/7/20
 * @description
 */
@Service
@Transactional
public class ExamineServiceImpl extends BaseServiceImpl<Examine> implements ExamineService {

    @Autowired
    //这个注入对象和BaseServiceImpl的注入对象是一致的，层级不同
            ExamineMapper examineMapper;


    @Override
    public PageInfo<Examine> selectPages(int pageNum, int pageSize, Map<String,Object> params) {
        PageHelper.startPage(pageNum,pageSize);
        List<Examine> list = examineMapper.selectPage(params);

        //后台处理
        Page page=(Page) list;
        if (page.getPages()<pageNum){
            PageHelper.startPage(1,pageSize);
            list=examineMapper.selectPage(params);
        }

        PageInfo<Examine> pageInfo = new PageInfo<>(list);
        return pageInfo;


        /*PageHelper.startPage(pageNum, pageSize);
        List<Examine> list = examineMapper.selectPage(params);

        //后台处理
        Page page = (Page) list;
        if (page.getPages() < pageNum) {
            PageHelper.startPage(1, pageSize);//页码索引从1开始
            list = examineMapper.selectPage(params);//带条件查询
        }

        PageInfo<Examine> pageInfo = new PageInfo<>(list);
        return pageInfo;*/
    }
}
