package com.lyq.service.impl;

import com.lyq.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name) {
        return restTemplate.getForObject("http://service-hi/hi?name="+name,String.class);
    }

    public String hiError(String name){

        return  "hi,"+name+",sorry,error!ribbon";
    }
}
