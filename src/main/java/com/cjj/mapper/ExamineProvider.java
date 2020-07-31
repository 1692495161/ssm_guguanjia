package com.cjj.mapper;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author cjj
 * @date 2020/7/21
 * @description
 */
public class ExamineProvider {

    public String selectPage(Map<String,Object> params){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " +
                "    ex.*, " +
                "    su.NAME user_name, " +
                "    so.NAME office_name  " +
                "FROM " +
                "    examine ex, " +
                "    sys_user su, " +
                "    sys_office so  " +
                "WHERE " +
                "    ex.del_flag = 0 ");
        if (params.containsKey("type")&&!StringUtils.isEmpty(params.get("type"))){
            sb.append("and ex.type=#{type} ");
        }
        if (params.containsKey("officeId")&&!StringUtils.isEmpty(params.get("officeId"))){
            sb.append("and so.id=#{officeId} ");
        }
        sb.append("and     " +
                "ex.examine_user_id=su.id     " +
                "and     " +
                "su.office_id=so.id ");
        if (params.containsKey("name")&&!StringUtils.isEmpty(params.get("name"))){
            sb.append("and su.name like concat('%',#{name},'%') ");
        }
        return sb.toString();
    }

}
