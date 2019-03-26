package com.lyq.service;

import com.lyq.model.Ke;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface KeService {
    //查询课程
    HashMap<String,Object> queryKe(Integer page, Integer rows);

    //新增修改课程
    String saveKe(Ke ke, MultipartFile file);

    //回显课程
    Ke queryById(Integer id);

    //删除课程
    void deleteKe(Integer id);

    List<Ke> queryListKe();

}
