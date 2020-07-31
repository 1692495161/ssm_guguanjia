import com.cjj.config.SpringMyBatisConfig;
import com.cjj.entity.Examine;
import com.cjj.entity.Qualification;
import com.cjj.entity.QualificationCondition;
import com.cjj.mapper.ExamineMapper;
import com.cjj.service.QualificationService;
import com.cjj.service.impl.QualificationServiceImpl;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

/**
 * @author cjj
 * @date 2020/7/20
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMyBatisConfig.class)
public class TestQualification {
    @Autowired
    QualificationService qualificationService;

    @Autowired
    ExamineMapper mapper;

    @Test
    public void test(){
        QualificationCondition condition = new QualificationCondition();
        condition.setType("3");
        condition.setCheck("2");
        condition.setStartData("2019-01-01");
        condition.setEndData("2019-12-30");
        PageInfo<Qualification> pageInfo = qualificationService.selectPages(1, 2, condition);
    }
    @Test
    public void test2(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("officeId",56);
        map.put("type",1);
        map.put("name","人员");
        List<Examine> examines = mapper.selectPage(map);
        for (Examine examine : examines) {
            System.out.println(examine);
        }
    }

}
