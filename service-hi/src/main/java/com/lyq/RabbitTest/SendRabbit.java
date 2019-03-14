package com.lyq.RabbitTest;


import com.lyq.model.Tree;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendRabbit {
       @Autowired
      private AmqpTemplate amqpTemplate;

       @RequestMapping("sendHi")
       public String sendHi(){
           Tree tree = new Tree();
           tree.setText("hehe");
           tree.setId(1);
           tree.setPid(2);
           tree.setState("aaaa");
           amqpTemplate.convertAndSend("q_hi","sssss");
           return "成功";
       }
}
