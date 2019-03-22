package com.lyq.service;

import com.lyq.mapper.KeMapper;
import com.lyq.model.Ke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class KeServiceImpl implements KeService {

    @Autowired
    private KeMapper keMapper;

    //查询课程
    @Override
    public HashMap<String, Object> queryKe(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        //查询总条数
        long total = keMapper.queryKeTotal();
        //查询显示信息
        //开始位置
        int start = (page-1)*rows;
        List<Ke> list = keMapper.queryKe(start,rows);
        hashMap.put("total", total);
        hashMap.put("rows", list);
        return hashMap;
    }

    //新增修改课程
    @Override
    public void saveKe(Ke ke) {
        Integer id = ke.getKid();
        if(id!=null) {
            //修改课程
            keMapper.updateKe(ke);
        }else {
            //新增课程
            keMapper.saveKe(ke);
        }
    }

    //回显课程
    @Override
    public Ke queryById(Integer id) {
        return  keMapper.queryById(id);
    }

    //删除课程
    @Override
    public void deleteKe(Integer id) {
        keMapper.deleteKe(id);
    }

    @Override
    public List<Ke> queryListKe() {
        return keMapper.queryListKe();
    }
}
