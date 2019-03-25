package com.lyq.mapper;

import com.lyq.model.Ke;
import com.lyq.model.Mu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MuMapper {
    //查询目录总条数
    long queryMuTotal();

    //查询目录
    List<Mu> queryMu(@Param("start") int start, @Param("rows") Integer rows);

    //修改目录
    void updateMu(Mu mu);

    //新增目录
    void saveMu(Mu mu);

    //回显
    Mu queryById(Integer id);

    //删除目录
    void deleteMu(Integer id);

    //查询课程
    List<Ke> queryKe();
}
