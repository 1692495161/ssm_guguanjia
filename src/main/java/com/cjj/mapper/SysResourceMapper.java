package com.cjj.mapper;

import com.cjj.entity.SysResource;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResourceMapper extends Mapper<SysResource> {

    @Select("SELECT " +
            " sre.*  " +
            "FROM " +
            " sys_resource sre, " +
            " sys_role_resource srr  " +
            "WHERE " +
            " sre.del_flag = 0  " +
            " AND srr.role_id =#{rid} " +
            " AND srr.resource_id = sre.id")
    List<SysResource> selectByRid(long rid);

    @Select("SELECT " +
            "sre.id, " +
            "sre.`name`, " +
            "sre.common, " +
            "sre.icon, " +
            "sre.sort, " +
            "sre.parent_id, " +
            "sre.type, " +
            "sre.url, " +
            "sre.description, " +
            "sre.`status`, " +
            "sre.parent_ids, " +
            "sre.create_date, " +
            "sre.update_date, " +
            "sre.create_by, " +
            "sre.update_by, " +
            "sre.del_flag, " +
            "sre.permission_str  " +
            "FROM " +
            "sys_user_role sur, " +
            "sys_role sro, " +
            "sys_role_resource srr, " +
            "sys_resource sre  " +
            "WHERE " +
            "sre.del_flag = 0  " +
            "AND sre.type = 0  " +
            "AND sur.user_id = #{uid}  " +
            "AND sur.role_id = sro.id  " +
            "AND sro.id = srr.role_id  " +
            "AND srr.resource_id = sre.id ")
    List<SysResource> selectByUid(long uid);

    @Select("select * from sys_resource where del_flag=0 and type=0 and url <> '' ")
    List<SysResource> selectResources();
}