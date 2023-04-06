package com.example.pizzeriakotlin.entity

import lombok.Data
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Data
@NoArgsConstructor
@Table(name = "topping_user")
@EqualsAndHashCode(of = ["email"])
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long? = null

    @Column
    var email: @Email String? = null

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_topping_xref",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "topping_id")]
    )
    var toppings: Set<Topping> = hashSetOf()
}