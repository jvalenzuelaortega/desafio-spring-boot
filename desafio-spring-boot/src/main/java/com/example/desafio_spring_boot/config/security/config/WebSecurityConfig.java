package com.example.desafio_spring_boot.config.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.desafio_spring_boot.config.exception.security.CustomAccessDeniedHandler;
import com.example.desafio_spring_boot.config.exception.security.CustomAuthenticationEntryPoint;
import com.example.desafio_spring_boot.config.security.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final AuthenticationProvider authenticationProvider;
        private final CustomAccessDeniedHandler customAccessDeniedHandler;
        private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                        AuthenticationProvider authenticationProvider,
                        CustomAccessDeniedHandler customAccessDeniedHandler,
                        CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
                this.authenticationProvider = authenticationProvider;
                this.customAccessDeniedHandler = customAccessDeniedHandler;
                this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        }

        @Bean
        SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(rmr -> rmr
                                                .requestMatchers("/exercise/api**", "/error").permitAll()
                                                .requestMatchers("/h2-console/**").permitAll()
                                                .requestMatchers("/swagger-ui.html", "/swagger-ui/**",
                                                                "/v3/api-docs/**")
                                                .permitAll()
                                                .requestMatchers("/api/auth/get-token").permitAll()
                                                .anyRequest().authenticated())
                                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                                .sessionManagement(
                                                sessionManagement -> sessionManagement
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .exceptionHandling(exception -> exception.accessDeniedHandler(customAccessDeniedHandler)
                                                .authenticationEntryPoint(customAuthenticationEntryPoint));

                return httpSecurity.build();

        }
}
