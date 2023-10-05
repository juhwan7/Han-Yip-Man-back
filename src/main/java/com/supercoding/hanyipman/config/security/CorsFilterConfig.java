package com.supercoding.hanyipman.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsFilterConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("GET","POST", "PATCH", "PUT", "DELETE")
                .exposedHeaders("X-API-VERSION")
                .allowedOriginPatterns("*:*")
                .allowedHeaders("*");
    }

}
