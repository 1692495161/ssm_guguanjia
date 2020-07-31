package com.cjj.service;

import com.cjj.entity.SysArea;
import com.github.pagehelper.PageInfo;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author cjj
 * @date 2020/7/29
 * @description
 */
public interface AreaService extends BaseService<SysArea> {
    PageInfo<SysArea> selectById(int pageNum, int pageSize,long id);

    PageInfo<SysArea> selectByName(int pageNum, int pageSize,String name);

    int toDelete(SysArea area);

    /*
            读取数据库中sys_area 的所有记录  转成excel 输出流
            outputStream:控制层传递过来的response的输出流对象
            */
    void download(OutputStream outputStream);

    void upload(InputStream inputStream);
}
