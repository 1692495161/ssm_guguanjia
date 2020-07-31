package com.cjj.service.impl;

import com.cjj.entity.Demand;
import com.cjj.service.DemandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cjj
 * @date 2020/7/18
 * @description
 */
@Service
@Transactional
public class DemandServiceImpl extends BaseServiceImpl<Demand> implements DemandService {
    @Override
    public PageInfo<Demand> selectPages(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        Demand demand = new Demand();
        demand.setDelFlag("0");
        List<Demand> list = mapper.select(demand);
        //将分页处理成对象
        PageInfo<Demand> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
