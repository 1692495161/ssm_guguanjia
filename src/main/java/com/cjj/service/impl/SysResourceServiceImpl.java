package com.cjj.service.impl;

import com.cjj.entity.SysResource;
import com.cjj.mapper.SysResourceMapper;
import com.cjj.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cjj
 * @date 2020/8/4
 * @description
 */
@Service
@Transactional
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

    @Autowired
    SysResourceMapper resourceMapper;

    //加入redis缓存
    @Override
    @Cacheable(cacheNames = "resourceCache",key = "'com.cjj.service.impl.SysResourceServiceImpl.select.sysResource:'+#sysResource")
    public List<SysResource> select(SysResource sysResource) {
        return super.select(sysResource);
    }

    @Override
    public List<SysResource> selectByRid(long rid){
        return resourceMapper.selectByRid(rid);
    }

    @Override
    public List<SysResource> selectByUid(long uid){
        return resourceMapper.selectByUid(uid);
    }

    @Override
    public List<SysResource> selectResources(){
        return resourceMapper.selectResources();
    }
}
