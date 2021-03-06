package com.lyq.controller;


import com.lyq.model.SitesUser;
import com.lyq.service.TestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;


@RestController
public class LoginController {
    @Resource
    private TestService testService;

    @RequestMapping(value = "/login" )
    public String   login(SitesUser user, HttpServletRequest request){
   String a="ssss";
        SitesUser s =testService.login(user);
        if(s != null){
            if(s.getSitesName().equals(user.getSitesName())){
                if(s.getPassword().equals(user.getPassword())){
                    request.setAttribute("www",a);
                    request.getSession().setAttribute("www",a);
                    request.setAttribute("user",s);
                    request.getSession().setAttribute("user",s);
                    return "1";
                }
                return "密码不正确";
            }
            return "s为空";
    }

          return  "2";
    }
  //  注册
    @RequestMapping(value = "/regist" )
    public String regist(SitesUser user){
        return testService.regist(user);
    }

    @RequestMapping("/checkUser")
    public SitesUser checkUser(HttpServletRequest request, HttpServletResponse response){
        //编码规范
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //获取session值
        HttpSession session = request.getSession();
        SitesUser user = (SitesUser) session.getAttribute("user");

        return user;
    }
    //查询余额
    @RequestMapping("/queryBalance")
    public  SitesUser  queryBalance(HttpServletRequest request){
        SitesUser s= (SitesUser) request.getSession().getAttribute("user");
        Integer id=s.getId();
        return testService.queryBalance(id);
    }
    //套餐查询
    @RequestMapping(value = "/queryPa")
    public List queryPa () {
        return testService.queryPa();
    }
    //更改是否会员
    @RequestMapping(value = "/updateMem")
    public void updateMem(Integer id,HttpServletRequest request){
        SitesUser s= (SitesUser) request.getSession().getAttribute("user");
        Integer ids=s.getId();
        testService.updateMem( ids);
    }


}
