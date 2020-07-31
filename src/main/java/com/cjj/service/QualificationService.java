package com.cjj.service;

import com.cjj.entity.Qualification;
import com.cjj.entity.QualificationCondition;
import com.github.pagehelper.PageInfo;

public interface QualificationService extends BaseService<Qualification> {

    PageInfo<Qualification> selectPages(int pageNum, int pageSize, QualificationCondition condition);

    String getPath(Long uid);
}
