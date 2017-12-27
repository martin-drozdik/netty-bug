package com.example.demo

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findById
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class PersonRepository(val template: ReactiveMongoTemplate)
{
    fun findOne(id: String): Mono<Person> = template.findById<Person>(id)

    fun save(person: Person): Mono<Person> = template.save<Person>(person)
}
