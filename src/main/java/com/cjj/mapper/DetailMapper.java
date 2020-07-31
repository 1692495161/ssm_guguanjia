package com.cjj.mapper;

import com.cjj.entity.Detail;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface DetailMapper extends Mapper<Detail> {
    //根据工单id查询详单、危废大类、小类信息
    @Select("SELECT " +
            " de.*, " +
            " wt.NAME waste_type_name, " +
            " wt.CODE waste_type_code, " +
            " wa.CODE waste_code  " +
            "FROM " +
            " detail de, " +
            " waste_type wt, " +
            " waste wa  " +
            "WHERE " +
            " de.work_order_id = #{id}  " +
            " AND de.del_flag = 0  " +
            " AND de.waste_type_id = wt.id  " +
            " AND de.waste_id = wa.id")
    List<Map<String,Object>> selectById(long id);
}