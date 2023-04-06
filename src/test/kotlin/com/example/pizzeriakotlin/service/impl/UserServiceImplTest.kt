package com.example.pizzeriakotlin.service.impl

import com.example.pizzeriakotlin.dto.ToppingDTO
import com.example.pizzeriakotlin.dto.UserDTO
import com.example.pizzeriakotlin.entity.Topping
import com.example.pizzeriakotlin.entity.User
import com.example.pizzeriakotlin.repository.ToppingRepository
import com.example.pizzeriakotlin.repository.UserRepository
import com.example.pizzeriakotlin.service.UserService
import com.example.pizzeriakotlin.service.impl.ToppingServiceImplTest.Companion.buildToppings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.function.Consumer

internal class UserServiceImplTest {
    private val userRepository = Mockito.mock(UserRepository::class.java)
    private val toppingRepository = Mockito.mock(ToppingRepository::class.java)
    private var userService: UserService? = null
    @BeforeEach
    fun setup() {
        userService = UserServiceImpl(userRepository, toppingRepository)
    }

    @Test
    fun countToppings() {
        Mockito.`when`(userRepository.findAll()).thenReturn(buildUsers())
        val countToppings = userService!!.countToppings()
        Assertions.assertEquals(3, countToppings!!.size)
        Assertions.assertEquals(3, countToppings["Name 1"])
        Assertions.assertEquals(2, countToppings["Name 2"])
        Assertions.assertEquals(1, countToppings["Name 3"])
    }

    @get:Test
    val toppings: Unit
        get() {
            val userDTO = UserDTO()
            userDTO.id = 1L
            userDTO.email = "user@gmail.com"
            userDTO.toppingIds = hashSetOf(1L, 2L, 3L)
            Mockito.`when`(userRepository.findUserByEmail("user@gmail.com")).thenReturn(buildUser())
            val toppingDTOs = userService!!.getToppings(userDTO)
            Assertions.assertEquals(3, toppingDTOs!!.size)
            toppingDTOs.forEach(Consumer { toppingDTO: ToppingDTO? ->
                assertTrue(
                    userDTO.toppingIds!!.contains(toppingDTO?.id)
                )
            })
        }

    @Test
    fun processToppingsForNewUser() {
        val userDTO = UserDTO()
        userDTO.email = "user@gmail.com"
        userDTO.toppingIds = hashSetOf(1L, 2L, 3L)
        Mockito.`when`(userRepository.findUserByEmail(userDTO.email)).thenReturn(null)
        userService!!.processToppings(userDTO)
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any())
    }

    @Test
    fun processToppingsForExistedUser() {
        val userDTO = UserDTO()
        userDTO.email = "user@gmail.com"
        userDTO.toppingIds = hashSetOf(1L, 2L, 3L)
        Mockito.`when`(userRepository.findUserByEmail(userDTO.email)).thenReturn(buildUser())
        userService!!.processToppings(userDTO)
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
}