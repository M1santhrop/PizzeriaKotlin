package com.example.pizzeriakotlin.converter

import com.example.pizzeriakotlin.dto.UserDTO
import com.example.pizzeriakotlin.entity.Topping
import com.example.pizzeriakotlin.entity.User
import lombok.AccessLevel
import lombok.NoArgsConstructor

@NoArgsConstructor(access = AccessLevel.PRIVATE)
object UserConverter {
    fun convertUserDTOToUser(userDTO: UserDTO, toppings: Set<Topping>): User {
        val user = User()
        user.email = userDTO.email
        user.toppings = toppings.toHashSet()
        return user
    }
}