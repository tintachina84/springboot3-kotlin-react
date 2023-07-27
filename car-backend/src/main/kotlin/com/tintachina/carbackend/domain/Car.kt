package com.tintachina.carbackend.domain

import jakarta.persistence.*

/**
 * Car entity class.
 * @see <a href="https://www.baeldung.com/spring-data-rest-serialize-entity-id">Spring Data Rest에서 ID가 표시되지 않는 건에 대하여</a>
 */
@Entity
class Car(
    var brand: String,
    var model: String,
    var color: String,
    var registerNumber: String? = null,
    var year: Int,
    var price: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    var owner: Owner? = null
) {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    var id: Long = 0L
}