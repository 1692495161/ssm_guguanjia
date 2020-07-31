import com.alibaba.druid.pool.DruidDataSource;
import com.cjj.config.SpringMyBatisConfig;
import com.cjj.entity.AppVersion;
import com.cjj.mapper.AppVersionMapper;
import com.cjj.service.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * @author cjj
 * @date 2020/7/16
 * @description
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMyBatisConfig.class)
public class SsmTest {


    @Autowired
    DruidDataSource dataSource;

    @Autowired
    AppVersionMapper mapper;

    @Autowired
    AppVersionService appVersionService;

    @Test
    public void test() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void test2(){
        List<AppVersion> appVersions = mapper.selectAll();
        for (AppVersion appVersion : appVersions) {
            System.out.println(appVersion);
        }
    }

    @Test
    public void test3(){
        List<AppVersion> appVersions = mapper.selectAll();
        for (AppVersion appVersion : appVersions) {
            System.out.println(appVersion);
        }
    }

    @Test
    public void test4(){
        List<AppVersion> appVersions = appVersionService.selectAll();
        for (AppVersion appVersion : appVersions) {
            System.out.println(appVersion);
        }
    }

    @Test
    public void test5(){
        //分页查询
        PageHelper.startPage(1,5);
        List<AppVersion> appVersions = mapper.selectAll();

        //处理成  分页对象
        PageInfo<AppVersion> pageInfo = new PageInfo<>(appVersions);
        System.out.println(pageInfo);
    }
}
