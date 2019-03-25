package com.lyq.controller;

import com.lyq.model.Ke;
import com.lyq.model.Mu;
import com.lyq.service.MuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

//目录管理
@Controller
@RequestMapping("mu")
public class MuController {

    @Autowired
    private MuService muService;

    //查询课程
    @ResponseBody
    @RequestMapping("queryKe")
    public List<Ke> queryKe(){
       return muService.queryKe();
    }

    //查询目录
    @ResponseBody
    @RequestMapping("queryMu")
    public HashMap<String , Object> queryMu(Integer page, Integer rows){
        return muService.queryMu(page,rows);
    }

    //新增修改目录
    @RequestMapping("saveMu")
    @ResponseBody
    public void saveMu(Mu mu){
        muService.saveMu(mu);
    }

    //回显目录
    @RequestMapping("queryById")
    @ResponseBody
    public Mu queryById(Integer id){
        return muService.queryById(id);
    }

    //删除目录
    @RequestMapping("deleteMu")
    @ResponseBody
    public void deleteMu(Integer id){
        muService.deleteMu(id);
    }
}
