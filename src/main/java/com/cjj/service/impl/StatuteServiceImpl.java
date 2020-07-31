package com.cjj.service.impl;

import com.cjj.entity.Statute;
import com.cjj.mapper.StatuteMapper;
import com.cjj.service.StatuteService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cjj
 * @date 2020/7/23
 * @description
 */
@Service
@Transactional
public class StatuteServiceImpl extends BaseServiceImpl<Statute> implements StatuteService {

    @Autowired
    StatuteMapper statuteMapper;

    @Override
    public PageInfo<Statute> selectPages(int pageNum, int pageSize, Statute statute) {
        PageHelper.startPage(pageNum,pageSize);
        List<Statute> list = statuteMapper.select(statute);

        Page page=(Page) list;
        if (page.getPages()<pageNum){
            PageHelper.startPage(1,pageSize);
            list=statuteMapper.select(statute);
        }

        PageInfo<Statute> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
