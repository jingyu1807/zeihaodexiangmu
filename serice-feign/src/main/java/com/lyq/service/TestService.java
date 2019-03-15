package com.lyq.service;

import com.lyq.service.impl.TestServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-hi",fallback = TestServiceHystric.class )
public interface TestService {
     @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String hi(@RequestParam(value = "name") String name);

    @RequestMapping(value = "queryCatalog",method = RequestMethod.GET)
    List queryCatalog();
}
