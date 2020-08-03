package com.cjj.service;

import com.cjj.entity.SysUser;

import java.util.List;

public interface SysUserService extends BaseService<SysUser> {

    List<SysUser> selectByRid(long rid);

    List<SysUser> selectNoRole(long oid,long rid);
}
