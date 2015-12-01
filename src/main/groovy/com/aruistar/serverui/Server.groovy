package com.aruistar.serverui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.web.context.support.XmlWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

/**
 * Created by liurui on 15/11/25.
 * 服务入口,可以在此配置Spring MVC的相关信息,具体可以参考spring boot的官方文档 http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
 */
@SpringBootApplication
class Server {

//注释掉的内容是 flex项目相关配置,如果不需要flex支持,可以干掉
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
