package com.cjj.service.impl;

import com.alibaba.excel.EasyExcel;
import com.cjj.entity.SysArea;
import com.cjj.listener.SysAreaListener;
import com.cjj.mapper.SysAreaMapper;
import com.cjj.service.AreaService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author cjj
 * @date 2020/7/23
 * @description
 */
@Service
@Transactional
public class AreaServiceImpl extends BaseServiceImpl<SysArea> implements AreaService {

    @Autowired
    SysAreaMapper areaMapper;

    @Override
    public PageInfo<SysArea> selectById(int pageNum, int pageSize, long id) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysArea> list = areaMapper.selectById(id);
        Page page = (Page) list;
        if (page.getPages() < pageNum) {
            PageHelper.startPage(1, pageSize);
            list = areaMapper.selectById(id);
        }
        //处理成分页对象
        PageInfo<SysArea> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<SysArea> selectByName(int pageNum, int pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysArea> list = areaMapper.selectByName(name);

        Page page = (Page) list;
        if (page.getPages() < pageNum) {
            PageHelper.startPage(1, pageSize);
            list = areaMapper.selectByName(name);
        }

        PageInfo<SysArea> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 动态更新：
     * 1.动态更新当前区域的字段
     * 2.如果当前区域的oldParentIds和parentIds不一致，更新所有子区域的parentIds
     */
    @Override
    public int updateByPrimaryKeySelective(SysArea area) {
        int result = 0;
        try {
            //1.动态更新当前区域的字段
            result = areaMapper.updateByPrimaryKeySelective(area);
            //2.如果当前区域的oldParentIds和parentIds不一致，更新所有子区域的parentIds
            if (!area.getParentIds().equals(area.getOldParentIds())) {
                result += areaMapper.updateParentIds(area);
            }
        } catch (Exception e) {
            throw new RuntimeException("更新失败...");
        }
        return result;
    }

    @Override
    public int toDelete(SysArea area){
        return areaMapper.toDelete(area);
    }

    /*
    读取数据库中sys_area 的所有记录  转成excel 输出流
    outputStream:控制层传递过来的response的输出流对象
    */
    @Override
    public void download(OutputStream outputStream){
        SysArea sysArea = new SysArea();
        sysArea.setDelFlag("0");
        List<SysArea> list = mapper.select(sysArea);
        //转换成excel输出流
        EasyExcel.write(outputStream,SysArea.class).sheet().doWrite(list);
    }

    @Override
    public void upload(InputStream inputStream){
        EasyExcel.read(inputStream,SysArea.class,new SysAreaListener(areaMapper)).sheet().doRead();
    }

}
