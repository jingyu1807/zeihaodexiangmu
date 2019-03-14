package com.jy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringbootRabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitApplication.class, args);
    }

}
