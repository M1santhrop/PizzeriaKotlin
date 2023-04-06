package com.example.pizzeriakotlin.repository

import com.example.pizzeriakotlin.entity.Topping
import org.springframework.data.repository.CrudRepository

interface ToppingRepository : CrudRepository<Topping?, Long?> {
    fun findAllByIdIn(ids: Set<Long?>?): Set<Topping>
}