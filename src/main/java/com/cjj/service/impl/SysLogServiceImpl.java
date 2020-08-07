package com.cjj.service.impl;

import com.cjj.entity.SysLog;
import com.cjj.mapper.SysLogMapper;
import com.cjj.service.SysLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cjj
 * @date 2020/8/6
 * @description
 */
/**
 * propagation:事务传播行为
 *     REQUIRED ：必须有事务 ，如果没有事务存在  会自动开启事务  ，如果有事务存在，则加入当前事务
 *     SUPPORTS ：支持当前事务，如果当前没有事务，就以非事务方式执行
 *     MANDATORY：  必须有事务，如果没有事务则抛异常
 *     REQUIRES_NEW ：  不管有没有事务，方法都会开启一个独立事务
 *     NOT_SUPPORTED  以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
 *
 *
 * isolation:   事务隔离级别,默认按数据库的默认隔离级别处理
 * readOnly:    是否只读事务     默认非只读事务  可以增删查改  只读事务只支持查询
 * rollbackFor:  默认只有非检查异常才会回滚，检查异常需要自定义设置才会回滚
 */

@Service
@Transactional(readOnly = false,isolation = Isolation.DEFAULT)
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {

    @Autowired
    SysLogMapper logMapper;

    @Override
    public PageInfo<SysLog> selectPages(int pageNum, int pageSize, SysLog sysLog) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysLog> list = logMapper.selectPage(sysLog);

        Page page= (Page) list;
        if (page.getPages()<pageNum){
            PageHelper.startPage(1,pageSize);
            list=logMapper.selectPage(sysLog);
        }

        PageInfo<SysLog> logPageInfo = new PageInfo<>(list);
        return logPageInfo;
    }

    @Override
    //当前方法会独立开启事务进行操作
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int insertSelective(SysLog sysLog) {
        return super.insertSelective(sysLog);
    }
}
