package com.cjj.service;

import com.cjj.entity.WorkOrder;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface WorkOrderService extends  BaseService<WorkOrder> {

    /*
    * 重写通用分页，提供查询del_flag为0的数据
    * */
    PageInfo<WorkOrder> selectPage(int pageNum, int pageSize, Map<String, Object> params);

    Map<String,Object> selectDetail(long id);

}
