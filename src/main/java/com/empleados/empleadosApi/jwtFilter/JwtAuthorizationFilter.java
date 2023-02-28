package com.empleados.empleadosApi.jwtFilter;


import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private TokenUtils tokenJwtUtil = new TokenUtils();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        
                String bearerToken =request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ", "");

            UsernamePasswordAuthenticationToken userPAT = tokenJwtUtil.validateToken(token);
           SecurityContextHolder.getContext().setAuthentication(userPAT);
            }
        

        filterChain.doFilter(request, response);
}
}

