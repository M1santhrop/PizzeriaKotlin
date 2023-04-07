package com.example.pizzeriakotlin.service.impl

import com.example.pizzeriakotlin.converter.ToppingConverter.convertToppingsToToppingDTOs
import com.example.pizzeriakotlin.converter.UserConverter
import com.example.pizzeriakotlin.dto.ToppingDTO
import com.example.pizzeriakotlin.dto.UserToppingDTO
import com.example.pizzeriakotlin.entity.Topping
import com.example.pizzeriakotlin.entity.User
import com.example.pizzeriakotlin.repository.ToppingRepository
import com.example.pizzeriakotlin.repository.UserRepository
import com.example.pizzeriakotlin.service.ToppingService
import org.springframework.stereotype.Service

@Service
class ToppingServiceImpl(private val userRepository: UserRepository, private val toppingRepository: ToppingRepository) : ToppingService {
    override fun findAll(): List<ToppingDTO> {
        val toppings = toppingRepository.findAll() as List<Topping?>
        return convertToppingsToToppingDTOs(toppings)
    }

    override fun retrieveUsersCountByToppingName(): Map<String?, Int?> {
        val users = userRepository.findAll() as List<User?>
        return users
            .map { it!!.toppings }
            .flatMap{ it.toSet() }
            .groupingBy { it.name }.eachCount()
    }

    override fun getToppings(userToppingDTO: UserToppingDTO): List<ToppingDTO> {
        val user = userRepository.findUserByEmail(userToppingDTO.email)
        return if (user != null) convertToppingsToToppingDTOs(user.toppings) else emptyList()
    }

    override fun processToppings(userToppingDTO: UserToppingDTO) {
        val existedUser = userRepository.findUserByEmail(userToppingDTO.email)
        val toppings = toppingRepository.findAllByIdIn(userToppingDTO.toppingIds)
        if (existedUser == null) {
            val user: User = UserConverter.convertUserToppingDTOToUser(userToppingDTO, toppings)
            userRepository.save(user)
        } else {
            existedUser.toppings = toppings
            userRepository.save(existedUser)
        }
    }
}