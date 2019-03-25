package com.lyq.mapper;

import com.lyq.model.Ke;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeMapper {
    //查询课程总条数
    long queryKeTotal();

    //查询课程
    List<Ke> queryKe(@Param("start") int start, @Param("rows") Integer rows);

    //新增课程
    void saveKe(Ke ke);

    //修改课程
    void updateKe(Ke ke);

    //回显课程
    Ke queryById(Integer id);

    //删除课程
    void deleteKe(Integer id);

    List<Ke> queryListKe();
}
