
import com.cjj.config.SpringMyBatisConfig;
import com.cjj.entity.SysRole;
import com.cjj.entity.WorkOrder;
import com.cjj.mapper.SysRoleMapper;
import com.cjj.mapper.WorkOrderMapper;
import com.cjj.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMyBatisConfig.class)//加载dao层配置类
public class TestRole {


    @Autowired
    SysRoleMapper mapper;

    @Autowired
    SysRoleService service;



    @Test
    public void  page(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("dataScope","1");
        map.put("oid",56);
        map.put("name","员");

        /*List<SysRole> sysRoles = mapper.selectPage(map);
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }*/

        PageInfo<SysRole> sysRolePageInfo = service.selectPage(1, 5, map);
        for (SysRole sysRole : sysRolePageInfo.getList()) {
            System.out.println(sysRole);
        }

    }




}
