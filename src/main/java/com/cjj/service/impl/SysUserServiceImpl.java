package com.cjj.service.impl;

import com.cjj.entity.SysUser;
import com.cjj.mapper.SysUserMapper;
import com.cjj.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
