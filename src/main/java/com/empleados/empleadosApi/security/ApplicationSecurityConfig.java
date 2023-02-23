package com.empleados.empleadosApi.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;  
    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;
    
    @Autowired
    public ApplicationSecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint,
                          UserAuthenticationProvider userAuthenticationProvider,
                          PasswordEncoder passwordEncoder) {
        this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /*http
            // manejo de token xsrf token
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())     
                .and()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                //las links que comienzen con api/ 
                //deben ser user con role ADMIN
                //.antMatchers("/api/**").hasRole("ADMIN")
                //.antMatchers(HttpMethod.DELETE, "/api/empleados/**").hasAuthority("empleados:read")
                //.antMatchers(HttpMethod.POST, "/api/empleados/**").hasAuthority("empleado:write")
                //.antMatchers(HttpMethod.PUT, "/api/empleados/i/**").hasAuthority("empleado:write")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
                */

                http
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new UsernamePasswordAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), UsernamePasswordAuthFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/signIn", "/v1/signUp").permitAll()
                .anyRequest().authenticated();
    }
    

	
//version 2.7.0 o superior
/*
@Bean
protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
 http
        .authorizeRequest()
	.antMatchers("/").permitAll().
	.antMatchers("/").authenticated();
return http.build();
}
*/


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

     

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("12"))
                //Seteamos el rol que tendra el usuario
                .roles("ADMIN") // ROLE_ADMIN
                .authorities("empleado:read","empleado:write")
                .build();


        UserDetails prohibido = User.builder()
                .username("prohibido")
                .password(passwordEncoder.encode("123"))
                .roles("USER") 
                .authorities("empleado:none")
                .build();

                return new InMemoryUserDetailsManager(
                    admin,
                    prohibido
            );
    
        }
    
}
