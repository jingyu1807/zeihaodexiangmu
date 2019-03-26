package com.lyq.service;

import com.lyq.model.SitesUser;
import com.lyq.service.impl.TestServiceHystric;
import feign.Param;
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
    @RequestMapping(value = "/pay",consumes = "application/json")
    void alipay(@RequestBody String money);
    @RequestMapping(value = "pack/queryPackages",method = RequestMethod.GET)
    List queryPa();
    @RequestMapping(value = "sitesUser/sendMessage",method = RequestMethod.GET)
    void sendMessage();
  //查询余额queryBalance()
  @RequestMapping(value = "sitesUser/queryBalance",method = RequestMethod.GET)
    SitesUser queryBalance(@Param(value="id") Integer id);
   //更改是否是会员状态
   @RequestMapping(value = "sitesUser/updateMem",method = RequestMethod.GET)
    void updateMem(@Param(value = "id") Integer ids);
}
