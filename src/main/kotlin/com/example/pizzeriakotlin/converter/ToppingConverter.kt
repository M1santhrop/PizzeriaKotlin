package com.example.pizzeriakotlin.converter

import com.example.pizzeriakotlin.dto.ToppingDTO
import com.example.pizzeriakotlin.entity.Topping

object ToppingConverter {
    fun convertToppingsToToppingDTOs(toppings: Collection<Topping?>): List<ToppingDTO> {
        return toppings
            .map { obj: Topping? -> convertToppingToToppingDTO(obj) }
    }

    private fun convertToppingToToppingDTO(topping: Topping?): ToppingDTO {
        var toppingDTO = ToppingDTO()
        toppingDTO.id = topping?.id
        toppingDTO.name = topping?.name
        return toppingDTO
    }
}