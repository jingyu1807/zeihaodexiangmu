package com.lyq.service;

import com.lyq.mapper.MuMapper;
import com.lyq.model.Ke;
import com.lyq.model.Mu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MuServiceImpl implements MuService {

    @Autowired
    private MuMapper muMapper;

    //查询目录
    @Override
    public HashMap<String, Object> queryMu(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        //查询总条数
        long total = muMapper.queryMuTotal();
        //查询显示信息
        //开始位置
        int start = (page-1)*rows;
        List<Mu> list = muMapper.queryMu(start,rows);
        hashMap.put("total", total);
        hashMap.put("rows", list);
        return hashMap;
    }

    //新增修改目录
    @Override
    public void saveMu(Mu mu) {
        Integer id = mu.getMid();
        if(id!=null) {
            //修改
            muMapper.updateMu(mu);
        }else {
            //新增
            muMapper.saveMu(mu);
        }
    }

    //回显目录
    @Override
    public Mu queryById(Integer id) {
        return  muMapper.queryById(id);
    }

    //删除目录
    @Override
    public void deleteMu(Integer id) {
        muMapper.deleteMu(id);
    }

    //查询课程
    @Override
    public List<Ke> queryKe() {
        return muMapper.queryKe();
    }
}
