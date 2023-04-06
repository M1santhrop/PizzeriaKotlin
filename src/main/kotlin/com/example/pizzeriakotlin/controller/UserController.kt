package com.example.pizzeriakotlin.controller

import com.example.pizzeriakotlin.dto.UserDTO
import com.example.pizzeriakotlin.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @GetMapping
    fun showUserChoice(model: Model): String {
        model.addAttribute("user", UserDTO())
        return "userChoice"
    }

    @PostMapping
    fun processEmail(model: Model, userDTO: UserDTO): String {
        val toppingDTOs = userService.getToppings(userDTO)
        model.addAttribute("user", userDTO)
        model.addAttribute("toppings", toppingDTOs)
        return "userChoice"
    }
}