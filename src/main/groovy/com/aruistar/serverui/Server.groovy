package com.aruistar.serverui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.web.context.support.XmlWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

/**
 * Created by liurui on 15/11/25.
 */
@SpringBootApplication
class Server {
//    public static void main(String[] args) {
//        SpringApplication.run(Server.class, args)
//    }

//    public DispatcherServlet flexDispatcherServlet() {
//        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
//        appContext.setConfigLocation("classpath:/META-INF/flex-servlet.xml");
//        return new DispatcherServlet(appContext);
//    }
//
//    @Bean
//    public ServletRegistrationBean dispatcherRegistrationFlex() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(flexDispatcherServlet(), "/messagebroker/*");
//        registration.name = 'flex'
//        registration.setLoadOnStartup(1)
//        return registration;
//    }
}
