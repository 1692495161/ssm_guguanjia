import com.cjj.config.SpringMyBatisConfig;
import com.cjj.entity.SysLog;
import com.cjj.service.SysLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author cjj
 * @date 2020/8/7
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMyBatisConfig.class)
@WebAppConfiguration
public class SysLogTest {
    @Autowired
    SysLogService logService;

    @Test
    public void select(){
        SysLog sysLog = new SysLog();
//        sysLog.setType("1");
        logService.selectPages(1,5,sysLog);
    }
}
