package com.lyq.controller;

import com.lyq.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
     @Resource
     private TestService testService;

     @GetMapping(value = "/hi" )
    public List hi(@RequestParam String name){
         List list =testService.queryCatalog();

         return testService.queryCatalog();
     }


}
