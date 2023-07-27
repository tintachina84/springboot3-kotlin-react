package com.tintachina.carbackend.web

import com.tintachina.carbackend.domain.Car
import com.tintachina.carbackend.domain.CarRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CarController (
    val carRepository: CarRepository
) {
    @GetMapping("/cars")
    fun getCars(): Iterable<Car> = this.carRepository.findAll()
}
