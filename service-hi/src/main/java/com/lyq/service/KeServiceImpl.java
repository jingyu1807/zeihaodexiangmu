package com.lyq.service;

import com.lyq.mapper.KeMapper;
import com.lyq.model.Ke;
import com.lyq.utils.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Service
public class KeServiceImpl implements KeService {

    @Autowired
    private KeMapper keMapper;



    @Autowired
    private OSSClientUtil ossClient;

    //查询课程
    @Override
    public HashMap<String, Object> queryKe(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        //查询总条数
        long total = keMapper.queryKeTotal();
        //查询显示信息
        //开始位置
        int start = (page-1)*rows;
        List<Ke> list = keMapper.queryKe(start,rows);
        hashMap.put("total", total);
        hashMap.put("rows", list);
        return hashMap;
    }



    //新增修改课程
    @Override
    public String saveKe(Ke ke, MultipartFile file) {
        if (file == null || file.getSize() <= 0) {
            System.out.println("头像不能为空");
        }

        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        Integer id = ke.getKid();
        if(id!=null) {
            ke.setKimg(imgUrl);
            //修改
            keMapper.updateKe(ke);
        }else {
            //新增
            ke.setKimg(imgUrl);
            keMapper.saveKe(ke);
        }
        return imgUrl;

    }

    //回显课程
    @Override
    public Ke queryById(Integer id) {
        return  keMapper.queryById(id);
    }

    //删除课程
    @Override
    public void deleteKe(Integer id) {
        keMapper.deleteKe(id);
    }

    @Override
    public List<Ke> queryListKe() {
        return keMapper.queryListKe();
    }
}
