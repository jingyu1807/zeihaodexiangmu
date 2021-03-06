package com.lyq.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyq.mapper.SitesUserMapper;
import com.lyq.model.SitesUser;
import com.lyq.utils.CommonCanstant;
import com.lyq.utils.HttpClientUtil;
import com.lyq.utils.MD5Util;
import com.lyq.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class SitesUserServiceImpl implements SitesUserService {

    @Autowired
    private SitesUserMapper sitesUserMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //查询网站用户列表 并分页
    public Map<String, Object> querySitesUser(Integer page, Integer rows) {
        Page<SitesUser> pageHelper = PageHelper.startPage(page, rows);
        List<SitesUser> queryMoleList = sitesUserMapper.querySitesUser();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("total", pageHelper.getTotal());
        dataMap.put("rows", queryMoleList);
        return dataMap;
    }

    //新增网站用户
    public String addSitesUser(SitesUser sitesUser) {


        Set<String> set = new HashSet<String>();
        int n = 10;
        for (int i = 0; i < n; i++) {
            //math.random()随机产生0-1之间的小数
            Double random = Math.random();
            String  str = random.toString().substring(2, 11);
            set.add(str);
        }

        Iterator<String> it = set.iterator();
       String codeKey= it.next();



        if(redisTemplate.hasKey(codeKey)){
            Set<String> set1 = new HashSet<String>();
            int n1 = 10;
            for (int i = 0; i < n; i++) {
                //math.random()随机产生0-1之间的小数
                Double random = Math.random();
                String  str = random.toString().substring(2, 11);
                set.add(str);
            }

            Iterator<String> it1 = set.iterator();
            String uuid1= it.next();

            redisTemplate.opsForValue().set(uuid1,11);
            sitesUser.setReferCode(uuid1);
            sitesUserMapper.addSitesUser(sitesUser);
            //发送短信
            String url = CommonCanstant.SEND_MSG_URL;
            HashMap<String, Object> params = new HashMap<>();
            params.put("accountSid", CommonCanstant.SEND_MSG_ACCOUNT_ID);//开发者主账号ID
            params.put("to",sitesUser.getSitesPhone());//短信接收端手机号码集合
            String time = TimeUtil.format(new Date());
            params.put("timestamp",time);//时间戳
            String sigStr = CommonCanstant.SEND_MSG_ACCOUNT_ID+ CommonCanstant.SEND_MSG_TOKEN+time;
            params.put("sig", MD5Util.getMd532(sigStr));//签名。MD5(ACCOUNT SID + AUTH TOKEN + timestamp)。共32位（小写）
            params.put("templateid", "1120139740");//短信模板ID
            String seCode= "欢迎成为本站用户哦！";
            params.put("param", seCode+",3");//短信变量
            String post = HttpClientUtil.post(url, params);

            return "200";

        }else{
            redisTemplate.opsForValue().set(codeKey,11);
            sitesUser.setReferCode(codeKey);
            sitesUserMapper.addSitesUser(sitesUser);
            //发送短信
            String url = CommonCanstant.SEND_MSG_URL;
            HashMap<String, Object> params = new HashMap<>();
            params.put("accountSid", CommonCanstant.SEND_MSG_ACCOUNT_ID);//开发者主账号ID
            params.put("to",sitesUser.getSitesPhone());//短信接收端手机号码集合
            String time = TimeUtil.format(new Date());
            params.put("timestamp",time);//时间戳
            String sigStr = CommonCanstant.SEND_MSG_ACCOUNT_ID+ CommonCanstant.SEND_MSG_TOKEN+time;
            params.put("sig", MD5Util.getMd532(sigStr));//签名。MD5(ACCOUNT SID + AUTH TOKEN + timestamp)。共32位（小写）
            params.put("templateid", "1120139740");//短信模板ID
            String seCode= "欢迎成为本站用户哦！";
            params.put("param", seCode+",3");//短信变量
            String post = HttpClientUtil.post(url, params);
            return "200";
        }


    }

    //删除网站用户 逻辑删
    public void deleteSitesUser(SitesUser sitesUser) {
        sitesUserMapper.deleteSitesUser(sitesUser);
    }

    //修改回显
    public SitesUser querySitesUserList(SitesUser sitesUser) {
        return sitesUserMapper.querySitesUserList(sitesUser);
    }

    //修改网站用户
    public void updateSitesUser(SitesUser sitesUser) {
        sitesUserMapper.updateSitesUser(sitesUser);
    }

    //回收站 查询被删除的网站用户
    public Map<String, Object> queryRecycleSitesUser(Integer page, Integer rows) {
        Page<SitesUser> pageHelper = PageHelper.startPage(page, rows);
        List<SitesUser> queryMoleList = sitesUserMapper.queryRecycleSitesUser();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("total", pageHelper.getTotal());
        dataMap.put("rows", queryMoleList);
        return dataMap;
    }

    //查询所有正常用户
    public List<SitesUser> queryCommentSitesUser() {
        return sitesUserMapper.querySitesUser();
    }


    @Override
    public List<SitesUser> queryCheckUser() {
        return sitesUserMapper.queryCheckUser();
    }

    @Override
    public void checkUser(Integer id) {
        sitesUserMapper.checkUser(id);
        //手机号查询
        SitesUser s=sitesUserMapper.queryCheckUserById(id);
        String phone=s.getSitesPhone();
        if(s.getStatus()==1){
            String url = CommonCanstant.SEND_MSG_URL;
            HashMap<String, Object> params = new HashMap<>();
            params.put("accountSid", CommonCanstant.SEND_MSG_ACCOUNT_ID);//开发者主账号ID
            params.put("to",phone);//短信接收端手机号码集合
            String time = TimeUtil.format(new Date());
            params.put("timestamp",time);//时间戳
            String sigStr = CommonCanstant.SEND_MSG_ACCOUNT_ID+ CommonCanstant.SEND_MSG_TOKEN+time;
            params.put("sig", MD5Util.getMd532(sigStr));//签名。MD5(ACCOUNT SID + AUTH TOKEN + timestamp)。共32位（小写）
            params.put("templateid", "1120139740");//短信模板ID
            String seCode= "恭喜审核通过，欢迎成为本站用户哦！";
            params.put("param", seCode+",3");//短信变量
            String post = HttpClientUtil.post(url, params);

        }




    }





    public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";//放到session中的key

    //员工登录
    public SitesUser sitesUserLogin(SitesUser user, HttpSession session) {
       // String randomcodekey = (String) session.getAttribute(RANDOMCODEKEY);

            SitesUser s = sitesUserMapper.sitesUserLogin(user);

             session.setAttribute("user",s);
             System.out.println(session.getAttribute("user"));
        return s;

        }


    @Override
    public void updatePri(Integer id, HttpSession session) {
        SitesUser s= (SitesUser) session.getAttribute("user");
        Integer sid=s.getId();

        if(id==1){
            sitesUserMapper.updatePri1(sid);
        }else if(id==2){
            sitesUserMapper.updatePri2(sid);
        }else if(id==3){
            sitesUserMapper.updatePri3(sid);
        }
    }

    @Override
    public void updateBal(String referCode) {
        SitesUser s=sitesUserMapper.queryUser( referCode);
        Integer count=s.getCounts()+1;
        Integer balance=s.getBalance();

        if(count<=5){
            balance=balance+10;
        } else if(count<=20){
            balance=balance+15;
        }else{
            balance=balance+20;
        }
        s.setCounts(count);
        s.setBalance(balance);
        sitesUserMapper.updateSome(s);

    }

    @Override
    public SitesUser queryBalance(Integer id) {
        return  sitesUserMapper.queryBalance(id);
    }

    @Override
    public void updateMem(Integer id) {
        sitesUserMapper.updateMem( id);
    }


    @Override
    public SitesUser querySitesUserIds(Integer userId) {
        return sitesUserMapper.querySitesUserIds(userId);
    }
}
