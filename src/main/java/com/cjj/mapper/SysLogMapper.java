package com.cjj.mapper;

import com.cjj.entity.SysLog;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysLogMapper extends Mapper<SysLog> {

    @SelectProvider(type = SysLogProvider.class,method = "selectPage")
    List<SysLog> selectPage(SysLog sysLog);
}