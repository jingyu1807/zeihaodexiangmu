package com.lyq.service;

import com.lyq.mapper.TreeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {
     @Resource
     private TreeMapper treeMapper;
    @Override
    public String say() {
        return treeMapper.queryTreeList(-1).toString();
    }
}
