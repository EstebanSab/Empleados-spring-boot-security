package com.empleados.empleadosApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
public class WebSecurityConfig {
    

    private final UserDetailsService miUserDetailsService;
    private PasswordEncoder passwordeEncoder;
   
    @Autowired
    public WebSecurityConfig( 
        UserDetailsService miUserDetailsService,
        PasswordEncoder passwordeEncoder){
            this.miUserDetailsService=miUserDetailsService;
            this.passwordeEncoder=passwordeEncoder;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManager authManager){
       JWTauthenticationFilter jwtAuthenticationFilter = new JWTauthenticationFilter();
       jwtAuthenticationFilter.setAuthenticationManager(authManager);
       jwtAuthenticationFilter.setFilterProcessesUrl(
        "/v1/login");
        
        try {
            return http
                        .csrf().disable()
                        .authorizeRequests()
                        //.antMatchers(HttpMethod.POST, "/v1/login").permitAll()
                        .anyRequest()
                        .authenticated()
                        .and()
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .addFilter(jwtAuthenticationFilter)
                        .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                        .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean 
    AuthenticationManager authManager(HttpSecurity http)throws Exception{
        return http
            .getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(miUserDetailsService)
            .passwordEncoder(passwordeEncoder)
            .and()
            .build();

    }

   
}
