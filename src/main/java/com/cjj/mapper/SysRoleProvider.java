package com.cjj.mapper;

import com.cjj.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author cjj
 * @date 2020/8/3
 * @description
 */
public class SysRoleProvider {
    public String selectPage(Map<String,Object> params){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " +
                "sr.*, " +
                "so.NAME officeName  " +
                "FROM " +
                "sys_role sr, " +
                "sys_office so  " +
                "WHERE " +
                "sr.del_flag = 0 ");
        if (params.containsKey("dataScope") && !StringUtils.isEmpty(params.get("dataScope"))){
            sb.append("AND sr.data_scope = #{dataScope} ");
        }
        if (params.containsKey("oid") && !StringUtils.isEmpty(params.get("oid"))) {
            sb.append(" and so.id=#{oid} ");
        }

        if (params.containsKey("remarks") && !StringUtils.isEmpty(params.get("remarks"))) {
            sb.append(" and sr.remarks like CONCAT('%',#{remarks},'%') ");
        }

        if (params.containsKey("name") && !StringUtils.isEmpty(params.get("name"))) {
            sb.append(" AND sr.name like CONCAT('%',#{name},'%') ");
        }
        sb.append(" and sr.office_id=so.id ");
        return sb.toString();
    }

    public String insertBatch(@Param("rid")long rid, @Param("cids") List<Long> cids){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `sys_user_role`( `role_id`, `user_id`, `create_by`, `create_date`, " +
                "`update_by`, `update_date`, `del_flag`) VALUES ");

        for (int i = 0; i < cids.size(); i++) {
            sb.append("(#{rid},#{cids["+i+"]},null,now(),null,now(),0),");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String deleteBatch(@Param("rid") long rid, @Param("ids") long[] ids){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE  from sys_user_role where role_id=#{rid} and user_id in ");
        sb.append("(");
        for (int i = 0; i < ids.length; i++) {
            sb.append("#{ids["+i+"]},");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }
}
