package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.OPTIONS
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration
{
    @Bean
    fun passwordEncoder(): PasswordEncoder
    {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun configure(manager: ReactiveAuthenticationManager): SecurityWebFilterChain
    {
        val http: ServerHttpSecurity = http()
        http.authenticationManager(manager)

        http.httpBasic()
        http.formLogin().disable()

        val authorize = http.authorizeExchange()
        authorize.anyExchange().authenticated()

        http.csrf().disable()

        return http.build()
    }

    @Bean
    fun manager(udr: UserDetailsRepositoryImpl): ReactiveAuthenticationManager
    {
        val manager = UserDetailsRepositoryReactiveAuthenticationManager(udr)
        manager.setPasswordEncoder(passwordEncoder())
        return manager
    }
}