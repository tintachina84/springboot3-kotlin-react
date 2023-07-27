package com.tintachina.carbackend.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository: JpaRepository<Car, Long> {

    // Fetch cars by brand
    fun findByBrand(brand: String): List<Car>

    // Fetch cars by color
    fun findByColor(color: String): List<Car>
}