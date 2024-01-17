package com.Wkrzyz.HouseHoldIncomeManager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){

        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/_core").setViewName("_core");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/transfers").setViewName("transfers");
        registry.addViewController("/mainPage").setViewName("mainPage");
        registry.addViewController("/addtransfer").setViewName("addtransfer");
        registry.addViewController("/error").setViewName("error");

    }

}
