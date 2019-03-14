package com.jy.Rabbit;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendRabbit {
        @Autowired
        private AmqpTemplate amqpTemplate;

        @RequestMapping("q_hello")
        public  String q_hello(){
            String a="哈哈哈 ";
            amqpTemplate.convertAndSend("q_hello",a);
            return "成功";
        }
}
