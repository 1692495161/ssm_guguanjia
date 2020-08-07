package com.cjj.mapper;

import com.cjj.entity.SysOffice;
import com.cjj.entity.SysResource;
import com.cjj.entity.SysRole;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.*;
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

    //删除已选的权限资源
    @Delete("DELETE FROM sys_role_resource where role_id=#{rid}")
    int deleteByRid(long rid);

    @InsertProvider(type = SysRoleProvider.class,method = "InsertByRid")
    int InsertByRid(@Param("rid") long rid, @Param("resources") List<SysResource> resources);

    @Delete("DELETE FROM sys_role_office where role_id=#{rid}")
    int deleteOfficeByRid(long rid);

    @InsertProvider(type = SysRoleProvider.class,method = "InsertOfficeByRid")
    int InsertOfficeByRid(@Param("rid") long rid, @Param("offices") List<SysOffice> offices);
}