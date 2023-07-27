package com.tintachina.carbackend.domain

import jakarta.persistence.*

@Entity
class User(
    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var role: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long = 0L
}