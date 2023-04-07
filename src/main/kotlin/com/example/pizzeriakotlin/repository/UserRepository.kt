package com.example.pizzeriakotlin.repository

import com.example.pizzeriakotlin.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findUserByEmail(email: String?): User?
}