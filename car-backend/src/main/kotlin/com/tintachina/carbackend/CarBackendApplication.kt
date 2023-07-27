package com.tintachina.carbackend

import com.tintachina.carbackend.domain.*
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke // 직접 임포트해야 함 (Kotlin DSL issue)
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@SpringBootApplication
class CarBackendApplication

val beans = beans {
    bean("corsFilter") {
        val config = CorsConfiguration().apply {
            allowedOrigins = listOf("*")
            maxAge = 8000L
            addAllowedMethod("*")
            addAllowedHeader("*")
        }

//        val config = CorsConfiguration().applyPermitDefaultValues()

        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", config)
        }

        CorsFilter(source)
    }

    bean {
        val http = ref<HttpSecurity>()
        http {
            csrf { disable() }
            httpBasic { disable() }
            cors {  }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            authorizeRequests {
                authorize(AntPathRequestMatcher("/login", HttpMethod.POST.name()), permitAll)
                authorize(anyRequest, authenticated)
            }
            exceptionHandling {
                authenticationEntryPoint = ref<AuthEntryPoint>()
            }
            addFilterBefore(ref<AuthenticationFilter>(), UsernamePasswordAuthenticationFilter::class.java)
            securityMatcher("/**")
        }
        http.build()
    }

    bean {
        CommandLineRunner {
            println("start data initialization...")
            val ownerRepository = ref<OwnerRepository>()
            val owners = arrayListOf(
                Owner("John", "Johnson"),
                Owner("Mary", "Robinson"),
                Owner("Kate", "Smith"),
                Owner("James", "Brown"),
                Owner("David", "Williams"),
                Owner("Donald", "Trump")
            )

            ownerRepository.saveAll(owners)
            ownerRepository.findAll().forEach {
                println("owner data::$it")
            }

            val carRepository = ref<CarRepository>()
            val cars = arrayListOf(
                Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owners[0]),
                Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, owners[2]),
                Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000, owners[2]),
                Car("Mercedes", "A-Class", "Black", "DFG-3002", 2014, 35000, owners[3]),
                Car("Mercedes", "C-Class", "White", "SSJ-3002", 2014, 29000, owners[4]),
                Car("Mercedes", "E-Class", "Red", "SSJ-3002", 2014, 29000, owners[4])
            )

            carRepository.saveAll(cars)
            carRepository.findAll().forEach {
                println("car data::$it")
            }

            val userRepository = ref<UserRepository>()
            val users = arrayListOf(
                // user: user, password: user
                User("user", "\$2a\$10\$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue", "USER"),
                // user: admin, password: admin
                User("admin", "\$2a\$10\$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", "ADMIN")
            )

            userRepository.saveAll(users)
            userRepository.findAll().forEach {
                println("user data::$it")
            }

            println("data initialization done...")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CarBackendApplication>(*args) {
        addInitializers(beans)
    }
}
