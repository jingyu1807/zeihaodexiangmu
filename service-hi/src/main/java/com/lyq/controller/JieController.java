package com.lyq.controller;

import com.lyq.model.Jie;
import com.lyq.model.Zhang;
import com.lyq.service.JieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

//小节管理
@Controller
@RequestMapping("jie")
public class JieController {
    @Autowired
    private JieService jieService;

    //查询章
    @ResponseBody
    @RequestMapping("queryZhang")
    public List<Zhang> queryZhang(){
        return jieService.queryZhang();
    }

    //查询小节
    @ResponseBody
    @RequestMapping("queryJie")
    public HashMap<String , Object> queryJie(Integer page, Integer rows){
        return jieService.queryJie(page,rows);
    }

    //新增修改小节
    @RequestMapping("saveJie")
    @ResponseBody
    public void saveJie(Jie jie){
        jieService.saveJie(jie);
    }

    //回显小节
    @RequestMapping("queryById")
    @ResponseBody
    public Jie queryById(Integer id){
        return jieService.queryById(id);
    }

    //删除小节
    @RequestMapping("deleteJie")
    @ResponseBody
    public void deleteJie(Integer id){
        jieService.deleteJie(id);
    }
}
