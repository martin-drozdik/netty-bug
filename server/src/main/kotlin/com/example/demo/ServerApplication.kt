package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class ServerApplication
{
    @Bean
    fun manipulateRepository(personRepository: PersonRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {

        val person1 = Person("jim", passwordEncoder.encode("pwd1"))
        val person2 = Person("jon", passwordEncoder.encode("pwd2"))
        personRepository.save(person1)
        personRepository.save(person2)
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
