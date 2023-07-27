package com.tintachina.carbackend.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class CarRepositoryBehaviorSpec (
    @Autowired private val carRepository: CarRepository
): BehaviorSpec({
    given("CarRepository 객체 주입") {
        `when`("CarRepository 객체가 null이 아닐 때") {
            then("CarRepository 객체가 null이 아님을 확인한다.") {
                carRepository shouldNotBe null
            }
        }
    }

    given("주어진 데이터 없음") {
        `when`("전체 검색을 했을 때") {
            val carList: List<Car> = carRepository.findAll()
            then("리스트 사이즈가 0이 반환된다.") {
                carList.size shouldBe 0
            }
        }
    }

    given("주어진 데이터 4개") {
        `when`("전체 검색을 했을 때") {
            carRepository.save(Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owners[0]))
            carRepository.save(Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, owners[0]))
            carRepository.save(Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000, owners[0]))
            carRepository.save(Car("Toyota", "Camry", "Black", "KKO-0213", 2018, 39000, owners[0]))
            val carList: List<Car> = carRepository.findAll()
            then("리스트 사이즈가 4가 반환된다.") {
                carList.size shouldBe 4
            }
        }

        `when`("브랜드가 Toyota인 데이터를 검색했을 때") {
            val carList: List<Car> = carRepository.findByBrand("Toyota")
            then("리스트 사이즈가 2가 반환된다.") {
                carList.size shouldBe 2
                carList.forEach { car -> car.brand shouldBe "Toyota" }
            }
        }

        `when`("브랜드가 Ford인 데이터를 검색했을 때") {
            val carList: List<Car> = carRepository.findByBrand("Ford")
            then("리스트 사이즈가 1이 반환된다.") {
                carList.size shouldBe 1
                carList[0].brand shouldBe "Ford"
            }
        }

        `when`("색상이 White인 데이터를 검색했을 때") {
            val carList: List<Car> = carRepository.findByColor("White")
            then("리스트 사이즈가 1이 반환된다.") {
                carList.size shouldBe 1
                carList[0].color shouldBe "White"
            }
        }
    }
})
