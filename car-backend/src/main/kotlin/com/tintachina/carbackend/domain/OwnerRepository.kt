package com.tintachina.carbackend.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OwnerRepository: JpaRepository<Owner, Long> {
    fun findByFirstName(firstName: String): Optional<Owner>
}