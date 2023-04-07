package com.example.pizzeriakotlin.dto

class ToppingDTO {
    var id: Long? = null
    var name: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ToppingDTO) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name?.hashCode() ?: 0
    }
}