package com.cjj.mapper;

import com.cjj.entity.Qualification;
import com.cjj.entity.QualificationCondition;
import org.apache.ibatis.annotations.Select;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author cjj
 * @date 2020/7/20
 * @description
 */
public class QualificationProvider {

    public String selectPage(QualificationCondition condition){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " +
                "  qu.id, " +
                "  qu.upload_user_id, " +
                "  qu.type, " +
                "  qu.address, " +
                "  qu.`check`, " +
                "  qu.description, " +
                "  qu.check_user_id, " +
                "  qu.create_date, " +
                "  qu.update_date, " +
                "  qu.del_flag, " +
                "  qu.create_by, " +
                "  cu.name check_name, " +
                "  uu.name update_name " +
                "  FROM " +
                "  qualification AS qu " +
                "  LEFT JOIN sys_user uu ON qu.upload_user_id = uu.id " +
                "  LEFT JOIN sys_user cu ON qu.check_user_id = cu.id  " +
                "  WHERE "+
                "qu.del_flag = 0 ");
        if (!StringUtils.isEmpty(condition.getType())){
            sb.append("AND qu.type = #{type} ");
        }
        if (!StringUtils.isEmpty(condition.getCheck())){
            sb.append("AND qu.check= #{check} ");
        }
        if (!StringUtils.isEmpty(condition.getStartData())){
            sb.append("AND qu.create_date >= #{startData} ");
        }
        if (!StringUtils.isEmpty(condition.getEndData())){
            sb.append("AND qu.create_date <= #{endData} ");
        }
        return sb.toString();
    }
}
