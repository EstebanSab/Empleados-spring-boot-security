package com.empleados.empleadosApi.jwtFilter;

import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


@Component
public class TokenUtils {

    //@Value("${security.jwt.token.secret-key:secret-key}")
    private static String secretKey="seguridadseguridadseguridadseguridad";

    

    public String createToken(String nombre,Long id,String authorities) {
        //Claims claims = Jwts.claims().setAudience(authorities).setSubject(nombre).setId(""+id);

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setSubject(nombre)
                .setId(""+id)
                .claim("authorities", authorities)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public  UsernamePasswordAuthenticationToken validateToken(String token) {
        try{
            String nombre = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

                String authorities = (String) Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("authorities");


        return new UsernamePasswordAuthenticationToken(nombre, null, /*Collections.emptyList()*/setAuthorities(authorities));
        }catch(JwtException jwte){
            return null;
        }
    }


    public List<GrantedAuthority> setAuthorities(String authorities){
        System.out.println(authorities);
        String[] aut =authorities.split(",");
        List<GrantedAuthority> roles = new ArrayList<>();

            for (String autS : aut) {
                roles.add(new SimpleGrantedAuthority(autS));
            }
            return roles;
    }


    public static  String getName(String token){
        return Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }
}