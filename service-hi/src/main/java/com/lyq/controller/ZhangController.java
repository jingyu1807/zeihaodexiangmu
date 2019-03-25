package com.lyq.controller;

import com.lyq.model.Mu;
import com.lyq.model.Zhang;
import com.lyq.service.ZhangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

//章管理
@Controller
@RequestMapping("zhang")
public class ZhangController {

    @Autowired
    private ZhangService zhangService;

    //查询目录
    @ResponseBody
    @RequestMapping("queryMu")
    public List<Mu> queryMu(){
        return zhangService.queryMu();
    }

    //查询章
    @ResponseBody
    @RequestMapping("queryZhang")
    public HashMap<String , Object> queryZhang(Integer page, Integer rows){
        return zhangService.queryZhang(page,rows);
    }

    //新增修改章
    @RequestMapping("saveZhang")
    @ResponseBody
    public void saveZhang(Zhang zhang){
        zhangService.saveZhang(zhang);
    }

    //回显章
    @RequestMapping("queryById")
    @ResponseBody
    public Zhang queryById(Integer id){
        return zhangService.queryById(id);
    }

    //删除章
    @RequestMapping("deleteZhang")
    @ResponseBody
    public void deleteZhang(Integer id){
        zhangService.deleteZhang(id);
    }
}
