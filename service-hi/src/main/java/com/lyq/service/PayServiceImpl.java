package com.lyq.service;

import com.lyq.mapper.PayMapper;
import com.lyq.model.StudentPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl  implements PayService{
    @Autowired
    private PayMapper payMapper;

    @Override
    public List<StudentPay> queryPay() {
        return payMapper.queryPay();
    }
}
