package com.example.pizzeriakotlin.controller

import com.example.pizzeriakotlin.dto.UserToppingDTO
import com.example.pizzeriakotlin.service.ToppingService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/toppings")
class ToppingController(private val toppingService: ToppingService) {
    @GetMapping
    fun showToppingsForm(model: Model): String {
        model.addAttribute("toppings", toppingService.findAll())
        model.addAttribute("user", UserToppingDTO())
        return "toppings"
    }

    @PostMapping
    fun processToppings(@ModelAttribute("user") userToppingDTO: UserToppingDTO): String {
        toppingService.processToppings(userToppingDTO)
        return "redirect:/toppings"
    }

    @GetMapping("/count")
    fun showToppingsCount(model: Model): String {
        val countToppingUsers = toppingService.retrieveUsersCountByToppingName()
        model.addAttribute("countToppingUsers", countToppingUsers)
        return "toppingsCount"
    }
}