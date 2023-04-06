package com.example.pizzeriakotlin.controller

import com.example.pizzeriakotlin.dto.UserDTO
import com.example.pizzeriakotlin.service.ToppingService
import com.example.pizzeriakotlin.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/toppings")
class ToppingController(private val toppingService: ToppingService, private val userService: UserService) {
    @GetMapping
    fun showToppingsForm(model: Model): String {
        model.addAttribute("toppings", toppingService.findAll())
        model.addAttribute("user", UserDTO())
        return "toppings"
    }

    @PostMapping
    fun processToppings(@ModelAttribute("user") userDTO: UserDTO): String {
        userService.processToppings(userDTO)
        return "redirect:/toppings"
    }

    @GetMapping("/count")
    fun showToppingsCount(model: Model): String {
        val countToppings = userService.countToppings()
        model.addAttribute("toppingsCount", countToppings)
        return "toppingsCount"
    }
}