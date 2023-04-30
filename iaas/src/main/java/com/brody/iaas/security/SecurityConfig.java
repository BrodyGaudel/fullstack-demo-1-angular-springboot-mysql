package com.brody.iaas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    @Autowired
    private AuthenticationManager authenticationManager;


    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
                                             UserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();

                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                }).and()
                .authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/list/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/sec/list/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/sec/get/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.POST, "/api/sec/save/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/api/sec/delete/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/api/v1/delete/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/api/v1/clear/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.PUT, "/api/v1/update/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.PUT, "/api/v1/modify/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/v1/get/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(HttpMethod.GET, "/api/v1/find/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(HttpMethod.GET, "/api/v1/running/**").hasAnyAuthority(ADMIN, USER)
                .anyRequest().authenticated().and()
                .addFilterBefore(new JWTAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
