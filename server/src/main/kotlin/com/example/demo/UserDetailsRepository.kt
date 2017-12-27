package com.example.demo

import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class UserDetailsRepositoryImpl(val personRepository: PersonRepository): ReactiveUserDetailsService
{
    override fun findByUsername(id: String): Mono<UserDetails> =
        personRepository.findOne(id).cast(UserDetails::class.java)
}