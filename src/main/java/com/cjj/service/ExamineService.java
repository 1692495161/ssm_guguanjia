package com.cjj.service;

import com.cjj.entity.Examine;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ExamineService extends BaseService<Examine> {

    PageInfo<Examine> selectPages(int pageNum, int pageSize, Map<String,Object> params);

}
