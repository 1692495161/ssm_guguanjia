import com.alibaba.excel.EasyExcel;
import com.cjj.config.SpringMyBatisConfig;
import com.cjj.entity.SysArea;
import com.cjj.listener.SysAreaListener;
import com.cjj.mapper.SysAreaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author cjj
 * @date 2020/7/29
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMyBatisConfig.class)
public class TestSysArea {

    @Autowired
    SysAreaMapper mapper;

    @Test
    public void select(){
        /*List<SysArea> select = mapper.selectById(1);
        for (SysArea sysArea : select) {
            System.out.println(sysArea);
        }*/
        List<SysArea> areas = mapper.selectByName("区");
        System.out.println(areas);
    }

    @Test
    public void testExcel(){
        EasyExcel.read("C:\\Users\\admin\\Downloads\\表1.xls",SysArea.class,new SysAreaListener(mapper)).sheet().doRead();
    }
}
