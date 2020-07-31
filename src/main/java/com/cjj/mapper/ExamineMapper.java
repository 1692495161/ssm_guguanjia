package com.cjj.mapper;

import com.cjj.entity.Examine;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ExamineMapper extends Mapper<Examine> {
    @SelectProvider(type = ExamineProvider.class,method = "selectPage")
    List<Examine> selectPage(Map<String,Object> params);
}