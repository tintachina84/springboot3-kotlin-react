package com.tintachina.carbackend.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@JsonIgnoreProperties("hibernateLazyInitializer", "handler")
class Owner(firstName: String, lastName: String) {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    var ownerId: Long = 0L
    var firstName: String = ""
    var lastName: String = ""
    @JsonIgnore
    @OneToMany(cascade= [CascadeType.ALL], mappedBy="owner")
    val cars: List<Car>? = null
}