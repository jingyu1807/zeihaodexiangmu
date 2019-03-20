package com.lyq.controller;

import com.lyq.model.Packages;
import com.lyq.model.StudentPay;
import com.lyq.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("Pay")
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping("queryPay")
    @ResponseBody
    public List<StudentPay> queryPay(){

        return payService.queryPay();
    }
}
