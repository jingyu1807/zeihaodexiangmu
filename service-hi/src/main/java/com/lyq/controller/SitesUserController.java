package com.lyq.controller;

import com.lyq.model.SitesUser;
import com.lyq.service.SitesUserService;
import com.lyq.utils.CommonCanstant;
import com.lyq.utils.HttpClientUtil;
import com.lyq.utils.MD5Util;
import com.lyq.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

//网站用户登录控制层
@Controller
@RequestMapping("sitesUser")
public class SitesUserController {

    @Autowired
    private SitesUserService sitesUserService;

    //查询当前用户
    @ResponseBody
    @RequestMapping("querySitesUserIds")
    public SitesUser querySitesUserIds(Integer userId){
        return sitesUserService.querySitesUserIds(userId);
    }

    //查询网站用户列表 并分页
    @ResponseBody
    @RequestMapping("querySitesUser")
    public Map<String , Object> querySitesUser(Integer page, Integer rows){
        return sitesUserService.querySitesUser(page,rows);
    }
    //查询未审核网站用户
    @ResponseBody
    @RequestMapping("queryCheckUser")
    public List<SitesUser> queryCheckUser(){
        return sitesUserService.queryCheckUser();
    }
    //用户审核通过更改状态
    @ResponseBody
    @RequestMapping("checkUser")
    public void checkUser(Integer id){
        sitesUserService.checkUser(id);
    }

    //网站用户登录
    @ResponseBody
    @RequestMapping("sitesUserLogin")
    public SitesUser sitesUserLogin(@RequestBody SitesUser user, HttpSession session){
        return sitesUserService.sitesUserLogin(user,session);
    }

    //注销
    @RequestMapping("remove")
    public void remove(HttpSession session){
        session.removeAttribute("user");

    }


    //新增网站用户
    @ResponseBody
    @RequestMapping("addSitesUser")
    public String addSitesUser(@RequestBody SitesUser sitesUser){


       return sitesUserService.addSitesUser(sitesUser);
    }

    //删除网站用户 逻辑删
    @ResponseBody
    @RequestMapping("deleteSitesUser")
    public void deleteSitesUser(SitesUser sitesUser){
        sitesUserService.deleteSitesUser(sitesUser);
    }

    //修改回显
    @ResponseBody
    @RequestMapping("querySitesUserList")
    public SitesUser querySitesUserList(SitesUser sitesUser){
        return sitesUserService.querySitesUserList(sitesUser);
    }

    //修改网站用户
    @ResponseBody
    @RequestMapping("updateSitesUser")
    public void updateSitesUser(SitesUser sitesUser){
        sitesUserService.updateSitesUser(sitesUser);
    }

    //回收站 查询被删除的网站用户
    @ResponseBody
    @RequestMapping("queryRecycleSitesUser")
    public Map<String , Object> queryRecycleSitesUser(Integer page, Integer rows){
        return sitesUserService.queryRecycleSitesUser(page,rows);
    }

    //查询所有正常用户
    @ResponseBody
    @RequestMapping("queryCommentSitesUser")
    public List<SitesUser> queryCommentSitesUser(){
        return sitesUserService.queryCommentSitesUser();
    }
    //更改会员到期时间
    @ResponseBody
    @RequestMapping("updatePri")
    public void updatePri(Integer id,HttpSession session){
        sitesUserService.updatePri( id,session);
    }
    //根据输入的邀请码查询用户信息，并对余额进行修改
    @ResponseBody
    @RequestMapping("updateBal")
    public void  updateBal(String referCode){
        sitesUserService.updateBal(referCode);


    }
    //查看余额
    @ResponseBody
    @RequestMapping("queryBalance")
    public  SitesUser queryBalance(HttpSession session){
        SitesUser s= (SitesUser) session.getAttribute("user");
        return  sitesUserService.queryBalance(s);
    }
    //定时器
    @ResponseBody
    @RequestMapping("sendMessage")
    public static void timer1() {
          Timer timer = new Timer();
        timer.schedule(new TimerTask() {
       public void run() {
           String url = CommonCanstant.SEND_MSG_URL;
           String phone="15135303996";
           HashMap<String, Object> params = new HashMap<>();
           params.put("accountSid", CommonCanstant.SEND_MSG_ACCOUNT_ID);//开发者主账号ID
           params.put("to",phone);//短信接收端手机号码集合
           String time = TimeUtil.format(new Date());
           params.put("timestamp",time);//时间戳
           String sigStr = CommonCanstant.SEND_MSG_ACCOUNT_ID+ CommonCanstant.SEND_MSG_TOKEN+time;
           params.put("sig", MD5Util.getMd532(sigStr));//签名。MD5(ACCOUNT SID + AUTH TOKEN + timestamp)。共32位（小写）
           params.put("templateid", "1120139740");//短信模板ID
           String seCode= "欢迎成为vip用户哦！";
           params.put("param", seCode+",3");//短信变量
           String post = HttpClientUtil.post(url, params);

                   }
    }, 2000);// 设定指定的时间time,此处为2000毫秒
         }
}
