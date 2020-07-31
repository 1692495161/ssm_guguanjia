package com.cjj.service.impl;

import com.cjj.entity.AppVersion;
import com.cjj.mapper.AppVersionMapper;
import com.cjj.service.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author cjj
 * @date 2020/7/16
 * @description
 */
@Service
//开启全局事务
@Transactional
public class AppVersionServiceImpl extends BaseServiceImpl<AppVersion> implements AppVersionService {

    @Override
    public PageInfo<AppVersion> selectPages(int pageNum, int pageSize) {
        //开启分页插件
        PageHelper.startPage(pageNum,pageSize);
        AppVersion app=new AppVersion();
        app.setDelFlag("0");
        List<AppVersion> list = mapper.select(app);
        //将查询结果处理成分页对象
        PageInfo<AppVersion> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int insertSelective(AppVersion appVersion) {
        appVersion.setCreateDate(new Date());
        appVersion.setUpdateDate(new Date());
        appVersion.setDelFlag("0");
        return super.insertSelective(appVersion);
    }
}
