package com.cjj.mapper;

import com.cjj.entity.SysLog;
import org.springframework.util.StringUtils;

/**
 * @author cjj
 * @date 2020/8/7
 * @description
 */
public class SysLogProvider {
    public String selectPage(SysLog sysLog){
        StringBuilder sb=new StringBuilder();
        sb.append("SELECT     " +
                "  *      " +
                "FROM     " +
                "  sys_log      " +
                "WHERE  " +
                "  1=1  ");
        if (!StringUtils.isEmpty(sysLog.getType())){
            sb.append("and type=#{type}");
        }
        if (!StringUtils.isEmpty(sysLog.getDescription())){
            sb.append("AND description LIKE CONCAT('%',#{description},'%')");
        }
        return sb.toString();
    }
}
