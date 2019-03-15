package com.lyq.config;

//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class JspConfig {

//    @Bean
//    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
//        ConfigurableEmbeddedServletContainer factory = new TomcatEmbeddedServletContainerFactory();
//        factory.setDocumentRoot(new File("D:\\idea-space\\mayiit\\cloud_parent\\eureka_client\\src\\main\\webapp\\"));
//
//        return (EmbeddedServletContainerFactory) factory;
//    }
//   // @Bean
//    public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.setDocumentRoot(new File("D:\\idea-space\\mayiit\\cloud_parent\\eureka_client\\src\\main\\webapp\\"));
//        return factory;
//    }

}
