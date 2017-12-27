package com.example.demo

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.net.URI

@Component
class PersonHandler(val personRepository: PersonRepository)
{
    fun get(req: ServerRequest): Mono<ServerResponse>
    {
        return personRepository
            .findOne(req.pathVariable("id"))
            .flatMap { ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(it.toMono()) }
    }
}