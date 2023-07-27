package com.tintachina.carbackend.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CarRepositoryTests {
    private val log = LoggerFactory.getLogger(this.javaClass)!!

    @Autowired
    private lateinit var carRepository: CarRepository

    @BeforeEach
    fun setUp() {
        this.carRepository.deleteAll()
    }

    @Test
    fun `Assert that the car repository is not null`() {
        assert(this.carRepository != null)
    }

    @Test
    fun `Assert that the car repository is empty`() {
        var list: List<Car> = this.carRepository.findAll();
        assert(this.carRepository.findAll().isEmpty())
    }

    @Test
    fun `Assert that the car repository is not empty`() {
        this.carRepository.save(Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owners[0]))
        assert(this.carRepository.findAll().isNotEmpty())
    }

    @Test
    fun `Assert that the car repository has one car`() {
        this.carRepository.save(Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owners[0]))
        assert(this.carRepository.findAll().count() == 1)
    }

    @Test
    fun `Assert that the car repository has two cars`() {
        this.carRepository.save(Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owners[0]))
        this.carRepository.save(Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, owners[0]))
        assert(this.carRepository.findAll().count() == 2)
    }

    @Test
    fun `Assert that the car repository can search cars by name`() {
        this.carRepository.save(Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owners[0]))
        this.carRepository.save(Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, owners[0]))
        this.carRepository.save(Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000, owners[0]))
        this.carRepository.save(Car("Toyota", "Camry", "Black", "KKO-0213", 2018, 39000, owners[0]))

        assert(this.carRepository.findByBrand("Toyota").count() == 2)
    }

}