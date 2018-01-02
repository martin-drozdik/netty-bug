package com.example.demo

import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Repository
class PersonRepository
{
    val items = mutableListOf<Person>()

    fun findOne(id: String): Mono<Person> = items.find { it.id == id }!!.toMono()

    fun save(person: Person) = items.add(person)
}
