package com.cjj.service.impl;

import com.cjj.entity.SysOffice;
import com.cjj.entity.SysResource;
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
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> list = roleMapper.selectPage(params);
        Page page = (Page) list;
        if (page.getPages() < pageNum) {
            PageHelper.startPage(1, pageSize);
            list = roleMapper.selectPage(params);
        }

        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int insertBatch(long rid, List<Long> cids) {
        return roleMapper.insertBatch(rid, cids);
    }

    @Override
    public int deleteBatch(long rid, long[] ids) {
        return roleMapper.deleteBatch(rid, ids);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole sysRole) {
        int result = 0;
        //修改数据库信息
        roleMapper.updateByPrimaryKeySelective(sysRole);
        result++;
        //判断resources是否发生了变化
        List<SysResource> resources = sysRole.getResources();
        List<SysResource> oldResources = sysRole.getOldResources();
        if (resources != null && oldResources != null) {
            //新旧都不为空，判断他们是否一致，不一致则需要进行修改(长度不一致或者长度一致，但内容不一致)。否则不需要修改
            if (resources.size() != oldResources.size() || (resources.size() == oldResources.size() && !resources.containsAll(oldResources))) {
                //先删除原有的权限数据
                roleMapper.deleteByRid(sysRole.getId());
                //再添加新的权限数据
                roleMapper.InsertByRid(sysRole.getId(), resources);
            }
        }
        result++;

        //判断offices是否发生了变化
        List<SysOffice> offices = sysRole.getOffices();
        List<SysOffice> oldOffices = sysRole.getOldOffices();
        if (offices != null && oldOffices != null) {
            //新旧都不为空，判断他们是否一致，不一致则需要进行修改(长度不一致或者长度一致，但内容不一致)。否则不需要修改
            if (offices.size() != oldOffices.size() || (offices.size() == oldOffices.size() && !offices.containsAll(oldOffices))) {
                //先删除原有的机构数据
                roleMapper.deleteOfficeByRid(sysRole.getId());
                //再添加新的机构数据
                roleMapper.InsertOfficeByRid(sysRole.getId(), offices);
            }
        }
        //如果dataScope本为9，改为其他，也就是不能跨机构
        else if (offices == null && oldOffices != null) {
            //先删除原有的机构数据
            roleMapper.deleteOfficeByRid(sysRole.getId());
            //再添加新的机构数据
//            roleMapper.InsertOfficeByRid(sysRole.getId(), offices);
        }
        //如果dataScope本为其他，改为9，也就是能跨机构
        else if (offices != null && oldOffices == null) {
            //先删除原有的机构数据
            roleMapper.deleteOfficeByRid(sysRole.getId());
            //再添加新的机构数据
            roleMapper.InsertOfficeByRid(sysRole.getId(), offices);
        }
        result++;

        return result;
    }
}
