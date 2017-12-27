package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ServerApplication
{
    @Bean
    fun manipulateRepository(personRepository: PersonRepository) = CommandLineRunner {

        val person1 = Person("jim", "pwd1")
        val person2 = Person("jon","pwd2")
        personRepository.save(person1).block()
        personRepository.save(person2).block()
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
