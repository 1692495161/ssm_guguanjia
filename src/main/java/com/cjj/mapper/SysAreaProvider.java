package com.cjj.mapper;

import com.cjj.entity.SysArea;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author cjj
 * @date 2020/7/29
 * @description
 */
public class SysAreaProvider {

    public String selectByName(String name){
        return new SQL(){{
            SELECT("son.id, " +
                    " son.parent_id, " +
                    " son.parent_ids, " +
                    " son.`code`, " +
                    " son.`name`, " +
                    " son.type, " +
                    " son.create_by, " +
                    " son.create_date, " +
                    " son.update_by, " +
                    " son.update_date, " +
                    " son.remarks, " +
                    " son.del_flag, " +
                    " son.icon, " +
                    " parent.`name` parentName ");
            FROM("sys_area son,  " +
                      " sys_area parent ");
            WHERE("son.del_flag = '0' ");
            if (!StringUtils.isEmpty(name)){
                WHERE(" son.NAME LIKE concat( '%', #{name}, '%' ) ");
            }
            WHERE("son.parent_id = parent.id");
        }}.toString();
    }

    public String insertArea(@Param("areas") List<SysArea> areas){
        return new SQL(){{
            INSERT_INTO("sys_area");
            INTO_COLUMNS("`parent_id`, `parent_ids`, `code`, `name`, `type`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `icon`");
            for (int i = 0; i < areas.size(); i++) {
                INTO_VALUES("#{areas["+i+"].parentId}, #{areas["+i+"].parentIds}, #{areas["+i+"].code}, " +
                        "#{areas["+i+"].name}, #{areas["+i+"].type}, #{areas["+i+"].createBy}, " +
                        "now(), #{areas["+i+"].updateBy}, now(), " +
                        "#{areas["+i+"].remarks}, #{areas["+i+"].delFlag}, #{areas["+i+"].icon}");
                ADD_ROW();//添加一行 要不格式会错误，会将所有循环内容看成一行
            }

        }}.toString();
    }
}
