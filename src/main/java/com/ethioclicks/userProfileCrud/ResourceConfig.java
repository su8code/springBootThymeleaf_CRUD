package com.ethioclicks.userProfileCrud;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = "file:///home/subadev/IdeaProjects/spring%20boot%20users%20profile%20crud/user-photos/";
        registry.addResourceHandler("/profile/**").addResourceLocations(path);
    }
}