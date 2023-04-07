package com.example.pizzeriakotlin.converter

import com.example.pizzeriakotlin.dto.UserToppingDTO
import com.example.pizzeriakotlin.entity.Topping
import com.example.pizzeriakotlin.entity.User

object UserConverter {
    fun convertUserToppingDTOToUser(userToppingDTO: UserToppingDTO, toppings: Set<Topping>): User {
        val user = User()
        user.email = userToppingDTO.email
        user.toppings = toppings.toHashSet()
        return user
    }
}