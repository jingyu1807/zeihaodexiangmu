package com.jy.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfig {
      @Bean
    public Queue queue(){
          return new Queue("q_hello");
      }
}
