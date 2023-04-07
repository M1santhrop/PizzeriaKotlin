package com.example.pizzeriakotlin.service

import com.example.pizzeriakotlin.dto.ToppingDTO
import com.example.pizzeriakotlin.dto.UserToppingDTO

interface ToppingService {
    fun findAll(): List<ToppingDTO>
    fun retrieveUsersCountByToppingName(): Map<String?, Int>
    fun getToppings(userToppingDTO: UserToppingDTO): List<ToppingDTO>
    fun processToppings(userToppingDTO: UserToppingDTO)
}