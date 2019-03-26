package com.lyq.controller;


import com.lyq.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

@RestController
public class KeController {
    @Resource
    private TestService testService;

    //课程
    @RequestMapping(value = "/queryListKe" )
    public List ke(){
        return  testService.ke();
    }

}
