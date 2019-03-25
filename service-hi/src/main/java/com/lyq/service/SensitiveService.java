package com.lyq.service;

import com.lyq.model.Sensitive;

import java.util.HashMap;

public interface SensitiveService {
    //敏感词查询
    HashMap<String,Object> querySensitive(Integer page, Integer rows);

    //新增修改敏感词
    void saveSensitive(Sensitive sensitive);

    //回显敏感词
    Sensitive queryById(Integer id);

    //删除敏感词
    void deleteSensitive(Integer id);
}
