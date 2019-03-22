package com.lyq.mapper;

import com.lyq.model.Mu;
import com.lyq.model.Zhang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZhangMapper {
    //查询目录
    List<Mu> queryMu();

    //查询总章数
    long queryZhangTotal();

    //查询章
    List<Zhang> queryZhang(@Param("start") int start, @Param("rows") Integer rows);

    //修改章
    void updateZhang(Zhang zhang);

    //新增章
    void saveZhang(Zhang zhang);

    //回显章
    Zhang queryById(Integer id);

    //删除章
    void deleteZhang(Integer id);
}
