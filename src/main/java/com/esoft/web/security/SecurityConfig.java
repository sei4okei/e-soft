package com.esoft.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomUserDetailsService userDetailsService;
    private AuthenticationSuccessHandler authSuccessHandler;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService, AuthenticationSuccessHandler authSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register","/register/**", "/css/**", "/js/**")
                        .permitAll()
                        .requestMatchers("/implementer/**", "task/{taskId}/edit", "/task/{implementerId}/new", "/task/{taskId}/delete")
                        .hasAuthority("MANAGER")
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                    .loginPage("/login")
                        .successHandler(authSuccessHandler)
                    .loginProcessingUrl("/login")
                    .failureUrl("/login?error")
                    .permitAll()
                ).logout(
                    logout -> logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }
}
