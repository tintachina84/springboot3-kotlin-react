package com.tintachina.carbackend.service

import com.tintachina.carbackend.domain.User
import com.tintachina.carbackend.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User.UserBuilder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    private val repository: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: Optional<User> = this.repository!!.findByUsername(username)
        var builder: UserBuilder? = null
        if (user.isPresent()) {
            val currentUser: User = user.get()
            builder = org.springframework.security.core.userdetails.User.withUsername(username)
            builder.password(currentUser.password)
            builder.roles(currentUser.role)
        } else {
            throw UsernameNotFoundException("User not found.")
        }
        return builder.build()
    }
}