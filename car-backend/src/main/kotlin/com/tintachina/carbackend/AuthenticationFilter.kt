package com.tintachina.carbackend

import com.tintachina.carbackend.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


@Component
class AuthenticationFilter : OncePerRequestFilter() {
    @Autowired
    private val jwtService: JwtService? = null

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        // Get token from Authorization header
        val jws = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (jws != null) {
            // Verify token and get user
            val user: String? = jwtService!!.getAuthUser(request)

            // Authenticate
            val authentication: Authentication = UsernamePasswordAuthenticationToken(user, null, emptyList())
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }
}