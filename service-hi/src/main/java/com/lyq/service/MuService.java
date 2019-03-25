package com.lyq.service;

import com.lyq.model.Ke;
import com.lyq.model.Mu;

import java.util.HashMap;
import java.util.List;

public interface MuService {

    //查询目录
    HashMap<String,Object> queryMu(Integer page, Integer rows);

    //新增修改目录
    void saveMu(Mu mu);

    //回显目录
    Mu queryById(Integer id);

    //删除目录
    void deleteMu(Integer id);

    //查询课程
    List<Ke> queryKe();
}
