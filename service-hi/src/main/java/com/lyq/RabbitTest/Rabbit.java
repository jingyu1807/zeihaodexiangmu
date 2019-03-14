package com.lyq.RabbitTest;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_hello")
public class Rabbit {

    @RabbitHandler
    public void  reRabbit(String hello){
      System.out.println(hello);
      }
}
