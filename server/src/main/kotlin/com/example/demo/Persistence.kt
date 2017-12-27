package com.example.demo

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.count
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findById
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class PersonRepository(val template: ReactiveMongoTemplate)
{
    fun count(): Mono<Long> = template.count<Person>()

    fun findAll(): Flux<Person> = template.findAll<Person>()

    fun findOne(id: String): Mono<Person> = template.findById<Person>(id)

    fun save(person: Person): Mono<Person> = template.save<Person>(person)
}
