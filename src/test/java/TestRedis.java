
import com.cjj.config.SpringMyBatisConfig;
import com.cjj.entity.SysOffice;
import com.cjj.entity.WorkOrder;
import com.cjj.mapper.SysOfficeMapper;
import com.cjj.mapper.WorkOrderMapper;
import com.cjj.service.SysOfficeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMyBatisConfig.class)//加载dao层配置类
public class TestRedis {


    @Autowired
    RedisConnectionFactory connectionFactory;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    SysOfficeMapper mapper;

    @Autowired
    SysOfficeService service;

    @Test
    public void testConn(){
        RedisConnection connection = connectionFactory.getConnection();
        Jedis jedis = (Jedis) connection.getNativeConnection();
        System.out.println(jedis.get("String操作"));
    }

    @Test
    public void testRedisTemplate(){
        /*根据不同的value的类型调用不同的api获取不同的值操作对象*/
        ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
        //redisTemplate的api会有自己的序列化和反序列化的处理对象，key经过序列化处理后，并不是"String操作"
        //注意旧的key：value不能使用
        //System.out.println(opsForValue.get("String操作"));  //结果为null
        opsForValue.set("redisTemplate2","redisTemplate序列化2");
        System.out.println(opsForValue.get("redisTemplate2"));
    }


    //不经过缓存器
    @Test
    public void testSer(){
        SysOffice sysOffice = new SysOffice();
        sysOffice.setDelFlag("0");
        List<SysOffice> offices = mapper.select(sysOffice);
        ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("office",offices);
        List<SysOffice> office2= (List<SysOffice>) valueOperations.get("office");
        for (SysOffice office : office2) {
            System.out.println(office);
        }
    }

    @Test
    public void testCache(){
        SysOffice sysOffice = new SysOffice();
        sysOffice.setDelFlag("0");
        List<SysOffice> offices = service.select(sysOffice);
        System.out.println(offices);
        System.out.println("---------------------------");
        //会直接从缓存中获取，不会再执行一次SQL语句
        offices=service.select(sysOffice);
        System.out.println(offices);
    }

    @Test
    public void testCacheConfig(){
        SysOffice sysOffice = new SysOffice();
        sysOffice.setDelFlag("0");
        List<SysOffice> offices = service.select(sysOffice);
        System.out.println(offices);
        System.out.println("---------------------------");
        //会直接从缓存中获取，不会再执行一次SQL语句
        offices=service.select(sysOffice);
        System.out.println(offices);
    }

    @Test
    public void testCacheConfig2(){
        SysOffice sysOffice = service.selectByPrimaryKey(2);
        /*sysOffice.setPhone("123456789");
        SysOffice office = service.updateByPrimaryKeySelectiveCache(sysOffice);
        System.out.println(office);*/
        System.out.println(sysOffice);
    }

    @Test
    public void testCacheEvict(){
        SysOffice sysOffice = service.selectByPrimaryKey(2);
        sysOffice.setPhone("123456789");
        service.updateByPrimaryKeySelective(sysOffice);
    }
}
