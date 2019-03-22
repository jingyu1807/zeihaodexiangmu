package com.lyq.service;

import com.lyq.mapper.ZhangMapper;
import com.lyq.model.Mu;
import com.lyq.model.Zhang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ZhangServiceImpl implements ZhangService {

    @Autowired
    private ZhangMapper zhangMapper;

    //查询目录
    @Override
    public List<Mu> queryMu() {
        return zhangMapper.queryMu();
    }

    //查询章
    @Override
    public HashMap<String, Object> queryZhang(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        //查询总条数
        long total = zhangMapper.queryZhangTotal();
        //查询显示信息
        //开始位置
        int start = (page-1)*rows;
        List<Zhang> list = zhangMapper.queryZhang(start,rows);
        hashMap.put("total", total);
        hashMap.put("rows", list);
        return hashMap;
    }

    //新增修改
    @Override
    public void saveZhang(Zhang zhang) {
        Integer id = zhang.getZid();
        if(id!=null) {
            //修改
            zhangMapper.updateZhang(zhang);
        }else {
            //新增
            zhangMapper.saveZhang(zhang);
        }
    }

    //回显章
    @Override
    public Zhang queryById(Integer id) {
        return zhangMapper.queryById(id);
    }

    //删除章
    @Override
    public void deleteZhang(Integer id) {
        zhangMapper.deleteZhang(id);
    }
}
