package com.example.pizzeriakotlin.dto

import lombok.NoArgsConstructor

@NoArgsConstructor
class UserToppingDTO {
    var email: String? = null
    var toppingIds: Set<Long>? = null
}