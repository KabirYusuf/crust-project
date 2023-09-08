package org.crustproject.auth;

import lombok.RequiredArgsConstructor;
import org.crustproject.auth.oauth.CustomAuthenticationFailureHandler;
import org.crustproject.auth.oauth.CustomAuthenticationSuccessHandler;
import org.crustproject.usermanagement.data.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.crustproject.auth.EndPointPermissionGrouping.*;

@Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class SecurityConfig {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final AuthenticationProvider authenticationProvider;
        private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
        private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
        private final AuthenticationEntryPoint authenticationEntryPoint;
        private final AccessDeniedHandler accessDeniedHandler;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf()
                    .disable()
//                    .exceptionHandling()
//                    .authenticationEntryPoint(authenticationEntryPoint)
//                    .and()
                    .authorizeHttpRequests()
                    .requestMatchers(userPermittedEndPoints()).hasAuthority(Role.USER.name())
                    .requestMatchers(noAuth())
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationProvider(authenticationProvider)
                    .oauth2Login()
                    .successHandler(customAuthenticationSuccessHandler)
                    .failureHandler(customAuthenticationFailureHandler)
                    .and()
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//                    .exceptionHandling()
//                    .accessDeniedHandler(accessDeniedHandler);


            return httpSecurity.build();

        }

    }


