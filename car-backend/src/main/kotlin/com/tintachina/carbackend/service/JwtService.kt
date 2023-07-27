package com.tintachina.carbackend.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*


@Component
class JwtService {

    val EXPIRATIONTIME: Long = 86400000 // 1 day in ms

    val PREFIX = "Bearer"

    // Generate secret key. Only for the demonstration
    // You should read it from the application configuration
    val key: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    // Generate JWT token
    fun getToken(username: String?): String? {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(key)
            .compact()
    }

    // Get a token from request Authorization header,
    // parse a token and get username
    fun getAuthUser(request: HttpServletRequest): String? {
        val token = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (token != null) {
            val user: String = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.replace(PREFIX, ""))
                .getBody()
                .getSubject()
            if (user != null) return user
        }
        return null
    }
}