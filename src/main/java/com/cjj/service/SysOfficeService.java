package com.cjj.service;


import com.cjj.entity.SysOffice;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface SysOfficeService extends BaseService<SysOffice> {
    /*
        @CachePut必定进入方法执行方法，并将处理结果对象更新
        */
    @CachePut(cacheNames = "officeCache",key = "'com.cjj.service.impl.SysOfficeServiceImpl.select:object:'+#sysOffice.id")
    SysOffice updateByPrimaryKeySelectiveCache(SysOffice sysOffice);

}
