package com.cjj.service.impl;

import com.cjj.entity.SysUser;
import com.cjj.mapper.SysUserMapper;
import com.cjj.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cjj
 * @date 2020/7/31
 * @description
 */
@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    @Autowired
    SysUserMapper userMapper;

    @Override
    public List<SysUser> selectByRid(long rid) {
        return userMapper.selectByRid(rid);
    }

    @Override
    public List<SysUser> selectNoRole(long oid, long rid) {
        return userMapper.selectNoRole(oid,rid);
    }
}
