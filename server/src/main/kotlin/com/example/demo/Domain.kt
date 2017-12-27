package com.example.demo

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class Person
(
    val id: String,
    val pwd: String

): UserDetails
{
    override fun getUsername() = id
    override fun getAuthorities() = mutableListOf<GrantedAuthority>()
    override fun isEnabled() = true
    override fun isCredentialsNonExpired() = true
    override fun getPassword() = pwd
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}
