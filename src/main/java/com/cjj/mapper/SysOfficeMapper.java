package com.cjj.mapper;

import com.cjj.entity.SysOffice;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysOfficeMapper extends Mapper<SysOffice> {

    @Select("SELECT " +
            "sof.*  " +
            "FROM " +
            "sys_office sof, " +
            "sys_role_office sro  " +
            "WHERE " +
            "sof.del_flag = 0  " +
            "AND sro.role_id = #{rid}  " +
            "AND sro.office_id = sof.id")
    List<SysOffice> selectOfficeByRid(long rid);
}