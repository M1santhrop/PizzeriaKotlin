package com.example.pizzeriakotlin.entity

import javax.persistence.*

@Entity
@Table
class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topping_id")
    var id: Long = 0

    @Column
    var name: String? = null
}