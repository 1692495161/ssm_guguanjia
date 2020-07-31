package com.cjj.service;

import com.cjj.entity.Statute;
import com.github.pagehelper.PageInfo;

public interface StatuteService extends BaseService<Statute> {


    PageInfo<Statute> selectPages(int pageNum, int pageSize,Statute statute);
}
