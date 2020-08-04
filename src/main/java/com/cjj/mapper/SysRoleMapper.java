package com.cjj.mapper;

import com.cjj.entity.SysRole;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper extends Mapper<SysRole>{

    @SelectProvider(type = SysRoleProvider.class ,method = "selectPage")
    List<SysRole> selectPage(Map<String,Object> params);


    @InsertProvider(type = SysRoleProvider.class,method = "insertBatch")
    int insertBatch(@Param("rid")long rid,@Param("cids") List<Long> cids);

    @DeleteProvider(type = SysRoleProvider.class,method="deleteBatch")
    int deleteBatch(@Param("rid") long rid,@Param("ids") long[] ids);
}