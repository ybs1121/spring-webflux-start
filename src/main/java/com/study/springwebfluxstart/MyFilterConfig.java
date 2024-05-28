package com.study.springwebfluxstart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {
    @Autowired
    private EventNotify eventNotify;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        System.out.println("필터가 등록됨");
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean(new MyFilter(eventNotify));
        filterRegistrationBean.addUrlPatterns("/sse");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2() {
        System.out.println("필터가 등록됨");
        FilterRegistrationBean<MyFilter2> filterRegistrationBean = new FilterRegistrationBean(new MyFilter2(eventNotify));
        filterRegistrationBean.addUrlPatterns("/add");
        return filterRegistrationBean;
    }
}
