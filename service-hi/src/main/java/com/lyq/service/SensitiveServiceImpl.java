package com.lyq.service;

import com.lyq.mapper.SensitiveMapper;
import com.lyq.model.Sensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SensitiveServiceImpl implements SensitiveService {
    @Autowired
    private SensitiveMapper sensitiveMapper;

    //查询
    @Override
    public HashMap<String, Object> querySensitive(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        //查询总条数
        long total = sensitiveMapper.querySensitiveTotal();
        //查询显示信息
        //开始位置
        int start = (page-1)*rows;
        List<Sensitive> list = sensitiveMapper.querySensitive(start,rows);
        hashMap.put("total", total);
        hashMap.put("rows", list);
        return hashMap;
    }

    //新增修改
    @Override
    public void saveSensitive(Sensitive sensitive) {
        Integer id = sensitive.getId();
        if(id!=null) {
            //修改
            sensitiveMapper.updateSensitive(sensitive);
        }else {
            //新增
            sensitiveMapper.saveSensitive(sensitive);
        }

    }

    //回显
    @Override
    public Sensitive queryById(Integer id) {
        return sensitiveMapper.queryById(id);
    }

    //删除
    @Override
    public void deleteSensitive(Integer id) {
        sensitiveMapper.deleteSensitive(id);
    }
}
