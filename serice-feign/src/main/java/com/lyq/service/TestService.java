package com.lyq.service;

import com.lyq.model.SitesUser;
import com.lyq.service.impl.TestServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-hi",fallback = TestServiceHystric.class )
public interface TestService {
     @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String hi(@RequestParam(value = "name") String name);

    @RequestMapping(value = "queryCatalog",method = RequestMethod.GET)
    List queryCatalog();

    @RequestMapping(value = "Pay/queryPay",method = RequestMethod.GET)
    List queryPay();
   //网站用户登录
    @RequestMapping(value = "sitesUser/sitesUserLogin",consumes = "application/json")
    SitesUser login( @RequestBody SitesUser user);
    //网站用户注册
    @RequestMapping(value = "sitesUser/addSitesUser",consumes = "application/json")
    String regist(@RequestBody SitesUser user);
}
