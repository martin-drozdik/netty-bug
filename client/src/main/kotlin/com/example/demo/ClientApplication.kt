package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import java.nio.charset.StandardCharsets
import java.util.*

data class Person
(
    var id: String,
    var pwd: String
)

@SpringBootApplication
class ClientApplication
{
    private val client = WebClient.builder().baseUrl("http://localhost:8080").build()

    @Bean
    fun run() = CommandLineRunner {

        val persons = listOf(Person("jim", "pwd1"), Person("jon", "pwd2"))
        while (true)
        {
            persons.forEach {
                var response = client
                    .get()
                    .uri("/api/person/jim")
                    .header("Authorization", basicAuth(it.id, it.pwd))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Person::class.java)
                    .block()

                println(response.id)
                }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ClientApplication>(*args)
}


fun basicAuth(username: String, password: String): String
{
    val credentialsString = username + ":" + password
    val credentialBytes = credentialsString.toByteArray(StandardCharsets.ISO_8859_1)
    val encodedBytes = Base64.getEncoder().encode(credentialBytes)
    val encodedCredentials = String(encodedBytes, StandardCharsets.ISO_8859_1)

    return "Basic " + encodedCredentials
}
