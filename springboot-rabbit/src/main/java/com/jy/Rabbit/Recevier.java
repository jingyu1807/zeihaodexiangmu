package com.jy.Rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_hi")
public class Recevier {
       @RabbitHandler
       public void RecevierHi(Object o){
           System.out.println(o.toString());
         }

}
