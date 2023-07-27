package com.tintachina.carbackend.config

import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.CorsRegistry

//@Component
class SpringDataRestConfig: RepositoryRestConfigurer {

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration, cors: CorsRegistry) {
        cors.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowCredentials(false)
            .maxAge(3600);
    }
}