package com.example.pizzeriakotlin.service.impl

import com.example.pizzeriakotlin.converter.ToppingConverter.convertToppingsToToppingDTOs
import com.example.pizzeriakotlin.entity.Topping
import com.example.pizzeriakotlin.repository.ToppingRepository
import com.example.pizzeriakotlin.service.ToppingService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

internal class ToppingServiceImplTest {
    private val toppingRepository = Mockito.mock(ToppingRepository::class.java)
    private var toppingService: ToppingService? = null
    @BeforeEach
    fun setup() {
        toppingService = ToppingServiceImpl(toppingRepository)
    }

    @Test
    fun findAll() {
        Mockito.`when`(toppingRepository.findAll()).thenReturn(buildToppings())
        val toppingDTOS = toppingService!!.findAll()
        Assertions.assertEquals(3, toppingDTOS!!.size)
        assertEquals(convertToppingsToToppingDTOs(buildToppings()), toppingDTOS)
    }

    companion object {
        @JvmStatic
        fun buildToppings(): Collection<Topping> {
            var topping1 = Topping()
            topping1.id = 1L
            topping1.name = "Name 1"
            val topping2 = Topping()
            topping2.id = 2L
            topping2.name = "Name 2"
            val topping3 = Topping()
            topping3.id = 3L
            topping3.name = "Name 3"
            return ArrayList(listOf(topping1, topping2, topping3))
        }
    }
}