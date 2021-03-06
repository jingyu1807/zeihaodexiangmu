package com.lyq.service;

import com.lyq.mapper.MembersMapper;
import com.lyq.model.MemPacka;
import com.lyq.model.Members;
import com.lyq.model.SitesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Service
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MembersMapper membersMapper;

    @Override
    public HashMap<String, Object> queryMemners(Integer page, Integer rows) {
        HashMap<String, Object> hashMap=new  HashMap<String, Object>();
        //查询总条数
         Long count=membersMapper.queryMemners();
         int start=(page-1)*rows;
         List<Members> list= membersMapper.queryMemnersPage(start,rows);
         hashMap.put("total",count);
         hashMap.put("rows",list);
         return hashMap;
    }

    @Override
    public List<Members> queryMem() {
        return membersMapper.queryMem();
    }

    @Override
    public String saveMem(MemPacka me, HttpSession session) {
        SitesUser user= (SitesUser) session.getAttribute("user");
        System.out.println(user.getId());
        me.setUid(user.getId());
        MemPacka memp=membersMapper.queryMemPackByuid(me.getUid());
            if(memp !=null){
              return  "您已是尊贵的会员无需购买";
            }
               membersMapper.saveMem(me);
            return  "购买成功";
    }

    @Override
    public Members queryBymId(Integer membersId) {
        return membersMapper.queryBymId(membersId);
    }

    @Override
    public void upMem(MemPacka me, HttpSession session) {
   /*     SitesUser user= (SitesUser) session.getAttribute("user");
        System.out.println(user.getId());
        me.setUid(user.getId());*/
/*        //获取登录人的Id去查询看他是否是管理员
        SitesUser   dbUser=membersMapper.queryRoleId(user.getId());*/

         membersMapper.upMem(me);
    }

}
