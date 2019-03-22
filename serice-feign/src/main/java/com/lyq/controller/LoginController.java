package com.lyq.controller;


import com.lyq.model.SitesUser;
import com.lyq.service.TestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.nio.file.Files;


@RestController
public class LoginController {
    @Resource
    private TestService testService;

    @RequestMapping(value = "/login" )
    public String   login(SitesUser user, HttpSession session){


          return  testService.login(user);
    }
  //  注册
    @RequestMapping(value = "/regist" )
    public String regist(SitesUser user){
        return testService.regist(user);
    }
}
