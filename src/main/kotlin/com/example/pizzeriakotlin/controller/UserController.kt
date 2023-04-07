package com.example.pizzeriakotlin.controller

import com.example.pizzeriakotlin.dto.UserToppingDTO
import com.example.pizzeriakotlin.service.ToppingService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/users")
class UserController(private val toppingService: ToppingService) {
    @GetMapping
    fun showUserChoice(model: Model): String {
        model.addAttribute("user", UserToppingDTO())
        return "userChoice"
    }

    @PostMapping
    fun processEmail(model: Model, userToppingDTO: UserToppingDTO): String {
        val toppingDTOs = toppingService.getToppings(userToppingDTO)
        model.addAttribute("user", userToppingDTO)
        model.addAttribute("toppings", toppingDTOs)
        return "userChoice"
    }
}