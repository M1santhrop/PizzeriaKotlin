package com.example.pizzeriakotlin.service.impl

import com.example.pizzeriakotlin.converter.ToppingConverter.convertToppingsToToppingDTOs
import com.example.pizzeriakotlin.dto.ToppingDTO
import com.example.pizzeriakotlin.entity.Topping
import com.example.pizzeriakotlin.repository.ToppingRepository
import com.example.pizzeriakotlin.service.ToppingService
import org.springframework.stereotype.Service

@Service
class ToppingServiceImpl(private val toppingRepository: ToppingRepository) : ToppingService {
    override fun findAll(): List<ToppingDTO> {
        val toppings = toppingRepository.findAll() as List<Topping?>
        return convertToppingsToToppingDTOs(toppings)
    }
}