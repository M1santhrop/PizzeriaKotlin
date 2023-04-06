package com.example.pizzeriakotlin.dto

import lombok.NoArgsConstructor
import java.io.Serializable

@NoArgsConstructor
class UserDTO : Serializable {
    var id: Long? = null
    var email: String? = null
    var toppingIds: Set<Long>? = null
}