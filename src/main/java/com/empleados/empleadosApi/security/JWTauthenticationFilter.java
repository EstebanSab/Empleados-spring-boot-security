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
    private TokenUtils tokenJwtUtil = new TokenUtils();

    @Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		//Creo un objeto de tipo AuthCredentials
        AuthCredentials authCredentials = new AuthCredentials();
        try{
            //Si en el request envio un objeto tipo authCredentials
            //lo mapeo al objeto creado
            authCredentials = new ObjectMapper().readValue(request.getReader(),AuthCredentials.class);
        }catch(IOException e){
            System.out.println("No se pueden validad las credenciales");
        }
        

        //List<GrantedAuthority> roles = new ArrayList<>();
        //roles.add(new SimpleGrantedAuthority("empleado:read"));

        //Creo un objeto de tipo upat
        UsernamePasswordAuthenticationToken userPAT= new UsernamePasswordAuthenticationToken(
            authCredentials.getUsuario(),  authCredentials.getPassword(),
            Collections.emptyList()/*roles*/);
		
        return getAuthenticationManager().authenticate(userPAT);
	}

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain chain,
		Authentication authResult) throws IOException, ServletException {
        
        UserDetailsImplementacion userImp= (UserDetailsImplementacion)authResult.getPrincipal();
        
        String token = tokenJwtUtil.createToken(userImp.getUsername(),userImp.getId(),userImp.getAuthoritiesString());
		
        response.addHeader("Authorization", "Bearer"+token);
        response.getWriter().flush();
        
        
        super.successfulAuthentication(request, response, chain, authResult);
	}
}
