package com.cjj.mapper;

import com.cjj.entity.Qualification;
import com.cjj.entity.QualificationCondition;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface QualificationMapper extends Mapper<Qualification> {
    @SelectProvider(type = QualificationProvider.class,method = "selectPage")
    List<Qualification> selectPage(QualificationCondition condition);
}