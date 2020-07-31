package com.cjj.mapper;

import com.cjj.entity.SysArea;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysAreaMapper extends Mapper<SysArea> {

    @Select("SELECT " +
            " son.id, " +
            " son.parent_id, " +
            " son.parent_ids, " +
            " son.`code`, " +
            " son.`name`, " +
            " son.type, " +
            " son.create_by, " +
            " son.create_date, " +
            " son.update_by, " +
            " son.update_date, " +
            " son.remarks, " +
            " son.del_flag, " +
            " son.icon, " +
            " parent.`name` parentName  " +
            "FROM " +
            " sys_area son, " +
            " sys_area parent  " +
            "WHERE " +
            " son.del_flag = '0'  " +
            " AND son.parent_ids LIKE concat( '%,', #{id}, ',%' )  " +
            "AND " +
            "son.parent_id=parent.id")
    List<SysArea> selectById(long id);

    @SelectProvider(type = SysAreaProvider.class,method = "selectByName")
    List<SysArea> selectByName(String name);

    /*根据父区域的旧parentIds和新parentIds进行更新所有的子区域的parentIds
     * 参数parentArea是父区域对象
     */
    @Update("UPDATE sys_area " +
            "SET parent_ids = REPLACE ( parent_ids, #{oldParentIds}, #{parentIds} )," +
            "update_date = NOW( )  " +
            "WHERE " +
            "parent_ids LIKE concat( '%,', #{id}, ',%' )")
    int updateParentIds(SysArea sysArea);

    @Update("UPDATE sys_area  " +
            "SET del_flag = #{delFlag}, " +
            "update_date = NOW( )  " +
            "WHERE " +
            " id = #{id}  " +
            " or  " +
            " parent_ids LIKE concat( '%,', #{id}, ',%' )")
    int toDelete(SysArea area);

    @InsertProvider(type = SysAreaProvider.class,method = "insertArea")
    int insertArea(@Param("areas") List<SysArea> sysAreas);
}