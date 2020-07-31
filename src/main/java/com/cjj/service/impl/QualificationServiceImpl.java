package com.cjj.service.impl;

import com.cjj.entity.Qualification;
import com.cjj.entity.QualificationCondition;
import com.cjj.entity.SysUser;
import com.cjj.mapper.QualificationMapper;
import com.cjj.mapper.SysUserMapper;
import com.cjj.service.BaseService;
import com.cjj.service.QualificationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cjj
 * @date 2020/7/20
 * @description
 */
@Service
@Transactional
public class QualificationServiceImpl extends BaseServiceImpl<Qualification> implements QualificationService {

    @Autowired
    //这个注入对象和BaseServiceImpl的注入对象是一致的，层级不同
            QualificationMapper qualificationMapper;

    @Autowired
    SysUserMapper userMapper;

    @Value("${path}")
    String path;

    @Override
    public PageInfo<Qualification> selectPages(int pageNum, int pageSize, QualificationCondition condition) {
        PageHelper.startPage(pageNum, pageSize);
        List<Qualification> list = qualificationMapper.selectPage(condition);

        //后台处理
        Page page = (Page) list;
        if (page.getPages() < pageNum) {
            PageHelper.startPage(1, pageSize);//页码索引从1开始
            list = qualificationMapper.selectPage(condition);//带条件查询
        }

        PageInfo<Qualification> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public String getPath(Long uid) {
        SysUser sysUser = userMapper.selectByPrimaryKey(uid);
        return path+sysUser.getOfficeId();
    }
}
