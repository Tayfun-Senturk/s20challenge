package com.workintech.s20challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/actuator/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/auth/register").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/products/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/api/products/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.GET, "/api/products/**").hasAnyAuthority("USER", "ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/api/orders/**").hasAnyAuthority("USER", "ADMIN");
                    auth.requestMatchers(HttpMethod.GET, "/api/orders/**").hasAnyAuthority("USER", "ADMIN");
                    auth.requestMatchers(HttpMethod.GET, "/api/order-items/**").hasAnyAuthority("USER", "ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/api/order-items/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/api/order-items/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.GET, "/api/categories/**").hasAnyAuthority("USER", "ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/api/categories/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasAuthority("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }
}
