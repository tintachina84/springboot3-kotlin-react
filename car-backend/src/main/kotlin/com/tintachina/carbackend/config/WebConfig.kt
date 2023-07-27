package com.tintachina.carbackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**")
//            .allowedOrigins("*")
//            .allowedMethods("*")
//            .allowCredentials(false)
//            .maxAge(3600);
//    }

//    @Bean
//    fun corsConfigurer(): WebMvcConfigurer {
//        return object : WebMvcConfigurer {
//            override fun addCorsMappings(registry: CorsRegistry) {
//                registry.addMapping("/**")
//                    .allowedOrigins("*")
//                    .allowedMethods("*")
//                    .allowCredentials(false)
//                    .maxAge(3600);
//            }
//        }
//    }
}