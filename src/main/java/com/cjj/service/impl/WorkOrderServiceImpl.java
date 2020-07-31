package com.cjj.service.impl;

import com.cjj.entity.WorkOrder;
import com.cjj.mapper.DetailMapper;
import com.cjj.mapper.TransferMapper;
import com.cjj.mapper.WorkOrderMapper;
import com.cjj.service.WorkOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WorkOrderServiceImpl extends BaseServiceImpl<WorkOrder> implements WorkOrderService {
    @Autowired
    WorkOrderMapper workOrderMapper;
    
    @Autowired
    DetailMapper detailMapper;
    
    @Autowired
    TransferMapper transferMapper;

    @Cacheable(cacheNames = "orderCache",
            key = "'com.cjj.service.impl.WorkOrderServiceImpl.selectPage'+ " +
                    "':pageNum:' +#pageNum+" +
                    "':pageSize:'+#pageSize+" +
                    "':params[status]:'+#params['status']")
    @Override
    public PageInfo<WorkOrder> selectPage(int pageNum, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum, pageSize);//页码索引从1开始

        List<WorkOrder> list = workOrderMapper.selectPage(params);//带条件查询

        //后台处理
        Page page = (Page) list;
        if (page.getPages() < pageNum) {
            PageHelper.startPage(1, pageSize);//页码索引从1开始
            list = workOrderMapper.selectPage(params);//带条件查询
        }
        //处理成分页对象
        PageInfo<WorkOrder> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /*
     * 根据工单id查询其详单信息
     * 1.根据工单id查询工单、用户、公司信息
     * 2.根据工单id查询转运详单
     * 3.根据工单id查询详单信息
     */
    @Override
    public Map<String, Object> selectDetail(long id) {
        Map<String, Object> workOrder = workOrderMapper.selectById(id);
        List<Map<String, Object>> detail = detailMapper.selectById(id);
        List<Map<String, Object>> transfer = transferMapper.selectById(id);

        workOrder.put("detail",detail);
        workOrder.put("transfer",transfer);

        return workOrder;
    }
}
