package com.example.pizzeriakotlin.service

import com.example.pizzeriakotlin.dto.ToppingDTO

interface ToppingService {
    fun findAll(): List<ToppingDTO?>?
}