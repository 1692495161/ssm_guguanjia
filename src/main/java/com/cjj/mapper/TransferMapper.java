package com.cjj.mapper;

import com.cjj.entity.Transfer;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TransferMapper extends Mapper<Transfer> {

    @Select("SELECT " +
            " tr.*, " +
            " su.NAME transfer_user_name, " +
            " su.phone transfer_user_phone  " +
            "FROM " +
            " transfer tr, " +
            " sys_user su  " +
            "WHERE " +
            " tr.work_order_id = #{id}  " +
            " AND tr.del_flag = 0  " +
            " AND tr.oprate_user_id = su.id order by tr.create_date desc")
    List<Map<String,Object>> selectById(long id);
}