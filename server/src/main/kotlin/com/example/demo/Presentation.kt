package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class ApiRoutes
(
    private val personHandler: PersonHandler
)
{
    @Bean
    fun apiRouter() = router {

        "/api".nest {

            "/person".nest {

                GET("/{id}", personHandler::get)

                POST("/{id}", personHandler::post)
            }
        }
    }
}