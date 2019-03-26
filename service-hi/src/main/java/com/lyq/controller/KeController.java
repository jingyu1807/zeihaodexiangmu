package com.lyq.controller;

import com.lyq.model.Ke;
import com.lyq.service.KeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//课程管理
@Controller
@RequestMapping("ke")
public class KeController {

    @Autowired
    private KeService keService;


    @ResponseBody
    @RequestMapping("queryListKe")
    public List<Ke> queryListKe(){
        return keService.queryListKe();
    }


    //查询课程
    @ResponseBody
    @RequestMapping("queryKe")
    public HashMap<String , Object> queryKe(Integer page, Integer rows){
        return keService.queryKe(page,rows);
    }



    //新增修改课程
    @RequestMapping("saveKe")
    @ResponseBody
    public void saveKe(Ke ke, MultipartFile file){

        Map<String, Object> value = new HashMap<String, Object>();
        value.put("success", true);
        value.put("errorCode", 0);
        value.put("errorMsg", "");

        String head =  keService.saveKe(ke,file);
        value.put("data", head);

    }

    //回显课程
    @RequestMapping("queryById")
    @ResponseBody
    public Ke queryById(Integer id){
        return keService.queryById(id);
    }

    //删除课程
    @RequestMapping("deleteKe")
    @ResponseBody
    public void deleteKe(Integer id){
        keService.deleteKe(id);
    }




}
