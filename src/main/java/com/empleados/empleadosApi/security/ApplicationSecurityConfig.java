package com.empleados.empleadosApi.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    
      /* */ private PasswordEncoder passwordEncoder;

      @Autowired
      public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
          this.passwordEncoder = passwordEncoder;
      }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                //las links que comienzen con api/ 
                //deben ser user con role ADMIN
                .antMatchers("/api/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

     

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("12"))
                //Seteamos el rol que tendra el usuario
                .roles("ADMIN") // ROLE_ADMIN
                .build();


        UserDetails prohibido = User.builder()
                .username("prohibido")
                .password(passwordEncoder.encode("123"))
                .roles("USER") 
                .build();

                return new InMemoryUserDetailsManager(
                    admin,
                    prohibido
            );
    
        }
    
}
