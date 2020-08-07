package com.cjj.service;

import com.cjj.entity.SysResource;

import java.util.List;

public interface SysResourceService extends BaseService<SysResource> {


    List<SysResource> selectByRid(long rid);

    List<SysResource> selectByUid(long uid);

    List<SysResource> selectResources();
}
