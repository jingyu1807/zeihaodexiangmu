package com.lyq.service;

import com.lyq.model.Jie;
import com.lyq.model.Zhang;

import java.util.HashMap;
import java.util.List;

public interface JieService {
    //查询章
    List<Zhang> queryZhang();

    //查询小节
    HashMap<String,Object> queryJie(Integer page, Integer rows);

    //新增修改小节
    void saveJie(Jie jie);

    //回显小节
    Jie queryById(Integer id);

    //删除小节
    void deleteJie(Integer id);
}
