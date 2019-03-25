package com.lyq.mapper;

import com.lyq.model.Jie;
import com.lyq.model.Zhang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JieMapper {
    //查询章
    List<Zhang> queryZhang();

    //查询小节总条数
    long queryJieTotal();

    //查询小节
    List<Jie> queryJie(@Param("start") int start, @Param("rows") Integer rows);

    //修改小节
    void updateJie(Jie jie);

    //新增小节
    void saveJie(Jie jie);

    //回显小节
    Jie queryById(Integer id);

    //删除小节
    void deleteJie(Integer id);
}
