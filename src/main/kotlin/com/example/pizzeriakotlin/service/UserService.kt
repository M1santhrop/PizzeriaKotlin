package com.example.pizzeriakotlin.service

import com.example.pizzeriakotlin.dto.ToppingDTO
import com.example.pizzeriakotlin.dto.UserDTO

interface UserService {
    fun countToppings(): Map<String?, Int?>?
    fun getToppings(userDTO: UserDTO): List<ToppingDTO>
    fun processToppings(userDTO: UserDTO)
}