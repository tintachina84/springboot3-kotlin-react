package com.tintachina.carbackend.web

import com.tintachina.carbackend.domain.AccountCredentials
import com.tintachina.carbackend.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
class LoginController {
    @Autowired
    private val jwtService: JwtService? = null

    @Autowired
    var authenticationManager: AuthenticationManager? = null

    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    fun getToken(@RequestBody credentials: AccountCredentials): ResponseEntity<*> {
        val creds = UsernamePasswordAuthenticationToken(
            credentials.username,
            credentials.password
        )
        val auth: Authentication = authenticationManager!!.authenticate(creds)

        // Generate token
        val jwts = jwtService!!.getToken(auth.getName())

        // Build response with the generated token
        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, "Bearer $jwts")
            .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
            .build<Any>()
    }
}