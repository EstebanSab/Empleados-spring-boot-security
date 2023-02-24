package com.empleados.empleadosApi.controller;


import java.sql.Date;
//import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


import com.empleados.empleadosApi.dto.CredentialsDto;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/v1")
public class AuthenticationController {

 
    @PostMapping("/login")
    public String signIn(@RequestBody CredentialsDto empleado) {
       String token = getJWTToken(empleado.getLogin());
        //User user = new User();
        //user.setUser(username);
        //user.setToken(token);		
    return token;
}

private String getJWTToken(String username) {
String secretKey = "mySecretKey";
List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList("ROLE_USER");

String token = Jwts
        .builder()
        .setId("softtekJWT")
        .setSubject(username)
        .claim("authorities",
                grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 600000))
        .signWith(SignatureAlgorithm.HS512,
                secretKey.getBytes()).compact();

return "Bearer " + token;
}

}

