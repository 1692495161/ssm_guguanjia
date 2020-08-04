package com.cjj.service.impl;

import com.cjj.entity.SysRole;
import com.cjj.mapper.SysRoleMapper;
import com.cjj.service.SysRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cjj
 * @date 2020/8/3
 * @description
 */
@Service
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
    @Autowired
    SysRoleMapper roleMapper;
    @Override
    public PageInfo<SysRole> selectPage(int pageNum, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysRole> list = roleMapper.selectPage(params);
        Page page= (Page) list;
        if (page.getPages()<pageNum){
            PageHelper.startPage(1,pageSize);
            list=roleMapper.selectPage(params);
        }

        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int insertBatch(long rid, List<Long> cids) {
        return roleMapper.insertBatch(rid,cids);
    }

    @Override
    public int deleteBatch(long rid, long[] ids) {
        return roleMapper.deleteBatch(rid, ids);
    }
}
