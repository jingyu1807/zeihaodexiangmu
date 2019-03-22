package com.lyq.mapper;

import com.lyq.model.Sensitive;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SensitiveMapper {

    //查询条数
    long querySensitiveTotal();

    //查询
    List<Sensitive> querySensitive(@Param("start") int start, @Param("rows") Integer rows);

    //新增
    void saveSensitive(Sensitive sensitive);

    //回显
    Sensitive queryById(Integer id);

    //删除
    void deleteSensitive(Integer id);

    //修改
    void updateSensitive(Sensitive sensitive);
}
