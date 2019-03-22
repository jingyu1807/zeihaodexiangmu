package com.lyq.service;

import com.lyq.model.Mu;
import com.lyq.model.Zhang;

import java.util.HashMap;
import java.util.List;

public interface ZhangService {
    //查询目录
    List<Mu> queryMu();

    //查询章
    HashMap<String,Object> queryZhang(Integer page, Integer rows);

    //新增修改章
    void saveZhang(Zhang zhang);

    //回显章
    Zhang queryById(Integer id);

    //删除章
    void deleteZhang(Integer id);
}
