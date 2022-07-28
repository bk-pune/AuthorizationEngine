package com.bk.config;

import com.bk.filter.AuthorizationFilter;
import com.bk.impl.DefaultAuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<AuthorizationFilter> ticketValidationFilterRegistration() {
        FilterRegistrationBean<AuthorizationFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(getAuthorizationFilter());
        registration.addUrlPatterns("/*");
        registration.setName("authorizationFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public AuthorizationFilter getAuthorizationFilter() {
        return new DefaultAuthorizationFilter("com.bk");
    }
}
