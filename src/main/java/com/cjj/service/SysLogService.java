package com.cjj.service;

import com.cjj.entity.SysLog;
import com.github.pagehelper.PageInfo;

public interface SysLogService extends BaseService<SysLog> {
    PageInfo<SysLog> selectPages(int pageNum, int pageSize, SysLog sysLog);
}
