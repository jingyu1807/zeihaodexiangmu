package com.lyq.controller;

import com.lyq.model.Sensitive;
import com.lyq.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

//敏感词管理
@Controller
@RequestMapping("sensitive")
public class SensitiveController {
    @Autowired
    private SensitiveService sensitiveService;

    //查询敏感词
    @ResponseBody
    @RequestMapping("querySensitive")
    public HashMap<String , Object> querySensitive(Integer page, Integer rows){
        return sensitiveService.querySensitive(page,rows);
    }

    //新增修改敏感词
    @RequestMapping("saveSensitive")
    @ResponseBody
    public void saveSensitive(Sensitive sensitive){
        sensitiveService.saveSensitive(sensitive);
    }

    //回显敏感词
    @RequestMapping("queryById")
    @ResponseBody
    public Sensitive queryById(Integer id){
        return sensitiveService.queryById(id);
    }

    //删除敏感词
    @RequestMapping("deleteSensitive")
    @ResponseBody
    public void deleteSensitive(Integer id){
        sensitiveService.deleteSensitive(id);
    }
}
