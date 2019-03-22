package com.lyq.service;

import com.lyq.mapper.JieMapper;
import com.lyq.model.Jie;
import com.lyq.model.Zhang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class JieServiceImpl implements JieService {

    @Autowired
    private JieMapper jieMapper;

    //查询章
    @Override
    public List<Zhang> queryZhang() {
        return jieMapper.queryZhang();
    }

    //查询小节
    @Override
    public HashMap<String, Object> queryJie(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        //查询总条数
        long total = jieMapper.queryJieTotal();
        //查询显示信息
        //开始位置
        int start = (page-1)*rows;
        List<Jie> list = jieMapper.queryJie(start,rows);
        hashMap.put("total", total);
        hashMap.put("rows", list);
        return hashMap;
    }

    //新增修改
    @Override
    public void saveJie(Jie jie) {
        Integer id = jie.getJid();
        if(id!=null) {
            //修改
            jieMapper.updateJie(jie);
        }else {
            //新增
            jieMapper.saveJie(jie);
        }
    }

    //回显小节
    @Override
    public Jie queryById(Integer id) {
        return jieMapper.queryById(id);
    }

    //删除小节
    @Override
    public void deleteJie(Integer id) {
        jieMapper.deleteJie(id);
    }
}
