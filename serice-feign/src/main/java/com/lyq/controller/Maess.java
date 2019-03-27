package com.lyq.controller;

import com.alibaba.fastjson.JSON;
import com.lyq.model.Messag;
import com.lyq.model.SitesUser;
import com.lyq.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Controller
public class Maess {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private TestService testService;

    //通过redis来实现数据的实时转发
    @ResponseBody
    @RequestMapping("addRedis")
    public void addRedis(Messag messag){
        String cacheKey = ConstantsConf.NAV_LIST_USERID;
        String treeJsonStr = JSON.toJSONString(messag);
        //信息存缓存
        redisTemplate.opsForList().rightPush(cacheKey, treeJsonStr);
        //设置缓存时间
        redisTemplate.expire(cacheKey, 30, TimeUnit.MINUTES);
        mongoTemplate.save(messag);
    }

    @RequestMapping("/ajax")
    public void ajax(HttpServletResponse response) throws Exception {
        Thread.sleep(1000);
        PrintWriter writer = response.getWriter();
        String cacheKey = ConstantsConf.NAV_LIST_USERID;
        //从缓存找:key是否存在
        Boolean hasKey = redisTemplate.hasKey(cacheKey);
        if(hasKey){
            Object o = redisTemplate.opsForList().rightPop(cacheKey);
            Messag messag = JSON.parseObject(o.toString(), Messag.class);
            writer.print(messag.getUserId()+","+messag.getMessage());
        }else{
            Thread.sleep(1000);
        }
    }

    @ResponseBody
    @RequestMapping("/queryUser")
    public SitesUser queryUser(Integer userId){
        SitesUser sitesUser = testService.queryUser(userId);
        return sitesUser;
    }
}
