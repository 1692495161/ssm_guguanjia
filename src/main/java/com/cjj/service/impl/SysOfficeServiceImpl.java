package com.cjj.service.impl;

import com.cjj.entity.SysOffice;
import com.cjj.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//缓存配置，设置整个类的全局缓存设置
@CacheConfig(cacheNames = "officeCache")
public class SysOfficeServiceImpl extends BaseServiceImpl<SysOffice> implements SysOfficeService {


    /*@Autowired
    RedisTemplate<Object, Object> redisTemplate;*/

    /*
     将查询所有office的功能实现添加缓存，放入redis
     1.从redis中获取，看是否为null，null则查询数据库
     2.不为null则直接返回
     */
    /*public List<SysOffice> select(SysOffice sysOffice) {
        //redis的key应该是缓存唯一的
        String key="com.cjj.service.impl.SysOfficeServiceImpl.select:sysOffice:"+sysOffice;
        Object obj = redisTemplate.opsForValue().get(key);
        List<SysOffice> offices;
        if (obj!=null){
            //在redis缓存中存在
            offices= (List<SysOffice>) obj;
        }else {
            //在redis缓存中不存在，从数据库查询
            offices=mapper.select(sysOffice);
            //加入redis缓存
            redisTemplate.opsForValue().set(key,offices);
        }
        return offices;
    }*/

    /*Cacheable注解：从指定缓存中(cacheNames对应的缓存对象)根据key对应查找是否存在数据，如果不存在
     则查询数据库，如果存在则直接从缓存中查询
     key:设置缓存的key  里面只能使用spring的EL表达式
     字符串通过''设置
     获取方法参数中的对象  #方法参数对象名
     获取方法参数中的对象属性/方法   #方法参数对象名.方法名()/#方法参数对象名.属性名
     获取方法参数中的map对象参数的key对应值 #方法参数对象名['key名']
     spring会自动根据配置创建缓存对象officeCache，动态代理当前方法生成判断缓存数据逻辑*/
    @Cacheable(cacheNames = "officeCache",key = "'com.cjj.service.impl.SysOfficeServiceImpl.select:sysOffice:'+#sysOffice")
    @Override
    public List<SysOffice> select(SysOffice sysOffice) {
        return super.select(sysOffice);
    }


    @Cacheable(cacheNames = "officeCache",key = "'com.cjj.service.impl.SysOfficeServiceImpl.select:object:'+#o")
    @Override
    public SysOffice selectByPrimaryKey(Object o) {
        return super.selectByPrimaryKey(o);
    }

    //@CachePut必定进入方法执行方法，并将处理结果对象更新
    @CachePut(cacheNames = "officeCache",key = "'com.cjj.service.impl.SysOfficeServiceImpl.select:object:'+#sysOffice.id")
    @Override
    public SysOffice updateByPrimaryKeySelectiveCache(SysOffice sysOffice){
        selectByPrimaryKey(sysOffice);
        return sysOffice;
    }

    //@CacheEvict 清空缓存中的数据 allEntries = true  增删改
    @CacheEvict(/*cacheNames = "officeCache",*/allEntries = true)
    @Override
    public int updateByPrimaryKeySelective(SysOffice sysOffice) {
        return super.updateByPrimaryKeySelective(sysOffice);
    }
}
