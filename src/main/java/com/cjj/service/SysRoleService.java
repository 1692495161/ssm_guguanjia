package com.cjj.service;

import com.cjj.entity.SysRole;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends BaseService<SysRole> {

    PageInfo<SysRole> selectPage(int pageNum, int pageSize, Map<String,Object> params);

    int insertBatch(long rid, List<Long> cids);

    int deleteBatch(long rid, long[] ids);


}
