package com.example.pizzeriakotlin.service.impl

import com.example.pizzeriakotlin.converter.ToppingConverter.convertToppingsToToppingDTOs
import com.example.pizzeriakotlin.dto.ToppingDTO
import com.example.pizzeriakotlin.dto.UserToppingDTO
import com.example.pizzeriakotlin.entity.Topping
import com.example.pizzeriakotlin.entity.User
import com.example.pizzeriakotlin.repository.ToppingRepository
import com.example.pizzeriakotlin.repository.UserRepository
import com.example.pizzeriakotlin.service.ToppingService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.function.Consumer

internal class ToppingServiceImplTest {
    private val toppingRepository = Mockito.mock(ToppingRepository::class.java)
    private val userRepository = Mockito.mock(UserRepository::class.java)
    private var toppingService: ToppingService? = null
    @BeforeEach
    fun setup() {
        toppingService = ToppingServiceImpl(userRepository, toppingRepository)
    }

    @Test
    fun findAll() {
        Mockito.`when`(toppingRepository.findAll()).thenReturn(buildToppings())
        val toppingDTOS = toppingService!!.findAll()
        Assertions.assertEquals(3, toppingDTOS!!.size)
        assertEquals(convertToppingsToToppingDTOs(buildToppings()), toppingDTOS)
    }

    @Test
    fun retrieveUsersCountByToppingName() {
        Mockito.`when`(userRepository.findAll()).thenReturn(buildUsers())
        val countToppingUsers = toppingService!!.retrieveUsersCountByToppingName()
        Assertions.assertEquals(3, countToppingUsers!!.size)
        Assertions.assertEquals(3, countToppingUsers["Name 1"])
        Assertions.assertEquals(2, countToppingUsers["Name 2"])
        Assertions.assertEquals(1, countToppingUsers["Name 3"])
    }

    @get:Test
    val toppings: Unit
        get() {
            val userToppingDTO = UserToppingDTO()
            userToppingDTO.email = "user@gmail.com"
            userToppingDTO.toppingIds = hashSetOf(1L, 2L, 3L)
            Mockito.`when`(userRepository.findUserByEmail("user@gmail.com")).thenReturn(buildUser())
            val toppingDTOs = toppingService!!.getToppings(userToppingDTO)
            Assertions.assertEquals(3, toppingDTOs!!.size)
            toppingDTOs.forEach(Consumer { toppingDTO: ToppingDTO? ->
                Assertions.assertTrue(
                    userToppingDTO.toppingIds!!.contains(toppingDTO?.id)
                )
            })
        }

    @Test
    fun processToppingsForNewUser() {
        val userToppingDTO = UserToppingDTO()
        userToppingDTO.email = "user@gmail.com"
        userToppingDTO.toppingIds = hashSetOf(1L, 2L, 3L)
        Mockito.`when`(userRepository.findUserByEmail(userToppingDTO.email)).thenReturn(null)
        toppingService!!.processToppings(userToppingDTO)
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any())
    }

    @Test
    fun processToppingsForExistedUser() {
        val userToppingDTO = UserToppingDTO()
        userToppingDTO.email = "user@gmail.com"
        userToppingDTO.toppingIds = hashSetOf(1L, 2L, 3L)
        Mockito.`when`(userRepository.findUserByEmail(userToppingDTO.email)).thenReturn(buildUser())
        toppingService!!.processToppings(userToppingDTO)
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any())
    }

    private fun buildUser(): User {
        val user = User()
        user.id = 1L
        user.email = "user@gmail.com"
        user.toppings = buildToppings().toHashSet()
        return user
    }

    private fun buildUsers(): List<User?> {
        val topping1 = Topping()
        topping1.name = "Name 1"
        val topping2 = Topping()
        topping2.name = "Name 2"
        val topping3 = Topping()
        topping3.name = "Name 3"
        val user1 = User()
        user1.id = 1L
        user1.email = "user1@gmail.com"
        user1.toppings = hashSetOf(topping1)
        val user2 = User()
        user2.id = 2L
        user2.email = "user2@gmail.com"
        user2.toppings = hashSetOf(topping1, topping2)
        val user3 = User()
        user3.id = 3L
        user3.email = "user3@gmail.com"
        user3.toppings = hashSetOf(topping1, topping2, topping3)
        return ArrayList(listOf(user1, user2, user3))
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