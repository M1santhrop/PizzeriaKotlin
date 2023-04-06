package com.example.pizzeriakotlin.service.impl

import com.example.pizzeriakotlin.converter.ToppingConverter.convertToppingsToToppingDTOs
import com.example.pizzeriakotlin.converter.UserConverter.convertUserDTOToUser
import com.example.pizzeriakotlin.dto.ToppingDTO
import com.example.pizzeriakotlin.dto.UserDTO
import com.example.pizzeriakotlin.entity.User
import com.example.pizzeriakotlin.repository.ToppingRepository
import com.example.pizzeriakotlin.repository.UserRepository
import com.example.pizzeriakotlin.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository, private val toppingRepository: ToppingRepository) :
    UserService {
    override fun countToppings(): Map<String?, Int?> {
        val users = userRepository.findAll() as List<User?>
        return users
            .distinct()
            .map { it!!.toppings }
            .flatMap{ it.toSet() }
            .groupingBy { it.name }.eachCount()
    }

    override fun getToppings(userDTO: UserDTO): List<ToppingDTO> {
        val user = userRepository.findUserByEmail(userDTO.email)
        return if (user != null) convertToppingsToToppingDTOs(user.toppings) else emptyList()
    }

    override fun processToppings(userDTO: UserDTO) {
        val existedUser = userRepository.findUserByEmail(userDTO.email)
        val toppings = toppingRepository.findAllByIdIn(userDTO.toppingIds)
        val user: User = convertUserDTOToUser(userDTO, toppings)
        if (existedUser == null) {
            userRepository.save(user)
        } else {
            existedUser.toppings = user.toppings
            userRepository.save(existedUser)
        }
    }
}