package com.chang.soloproject.solo_project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {



    }

    /**
     * 개발 시에만 CORS 허용
     */
    @Profile({"dev"})
    @Configuration
    public static class DevMvcConfiguration implements WebMvcConfigurer {
        public static final String ALL = "*";

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins(ALL)
                    .allowedMethods(ALL)
            ;
        }
    }
}
