package com.empleados.empleadosApi.security;

import java.util.Collections;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

    //@Value("${security.jwt.token.secret-key:secret-key}")
    private static String secretKey="seguridadseguridadseguridadseguridad";



    public static String createToken(String nombre,String id) {
        Claims claims = Jwts.claims().setSubject(nombre).setId(id);

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static UsernamePasswordAuthenticationToken validateToken(String token) {
        try{
            String id = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getId();


                List<GrantedAuthority> roles = new ArrayList<>();
                roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new UsernamePasswordAuthenticationToken(id, null, /*Collections.emptyList()*/roles);
        }catch(JwtException jwte){
            return null;
        }
    }
    public static  String getName(String token){
        return Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }
}