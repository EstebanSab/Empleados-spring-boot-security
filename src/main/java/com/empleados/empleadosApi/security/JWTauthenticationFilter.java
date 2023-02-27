package com.empleados.empleadosApi.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTauthenticationFilter extends UsernamePasswordAuthenticationFilter{
    

    @Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		//mi clase
        AuthCredentials authCredentials = new AuthCredentials();
        try{
            authCredentials = new ObjectMapper().readValue(request.getReader(),AuthCredentials.class);
        }catch(IOException e){

        }

        
        UsernamePasswordAuthenticationToken userPAT= new UsernamePasswordAuthenticationToken(
            authCredentials.getUsuario(), authCredentials.getPassword(),Collections.emptyList());
		
        return getAuthenticationManager().authenticate(userPAT);
	}

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
        
        UserDetailsImplementacion userImp= (UserDetailsImplementacion)authResult.getPrincipal();
        
        String token = TokenUtils.createToken(userImp.getNombre(),userImp.getUsername());
		
        response.addHeader("Authorization", "Bearer"+token);
        response.getWriter().flush();
        
        super.successfulAuthentication(request, response, null, authResult);
	}
}