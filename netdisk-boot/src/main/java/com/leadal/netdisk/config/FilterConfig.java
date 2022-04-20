//package com.leadal.netdisk.config;
//
//import com.leadal.cas.core.filter.CasFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<CasFilter> registrationBean(){
//        FilterRegistrationBean<CasFilter> registration = new FilterRegistrationBean<>(new CasFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("casFilter");
//        return registration;
//    }
//
//}
