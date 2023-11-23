package io.security.corespringsecurity.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationDetailsSource authenticationDetailsSource;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/", "/users").permitAll()
//                        .requestMatchers("/mypage").hasRole("USER")
                        .requestMatchers("/messages").hasRole("MANAGER")
                        .requestMatchers("/config").hasRole("ADMIN")
                        .requestMatchers("/admin/**").access((new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('SYS')")))
                        .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
//                .userDetailsService(userDetailsService)
                .authenticationProvider(authenticationProvider)
                .formLogin(login -> login
                        .authenticationDetailsSource(authenticationDetailsSource)
                        .successHandler(authenticationSuccessHandler)
                )
                .build();
    }

    //    @Bean
//    public UserDetailsManager users() {
//
//        String password = passwordEncoder().encode("1111");
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(password)
//                .roles("USER")
//                .build();
//
//        UserDetails sys = User.builder()
//                .username("manager")
//                .password(password)
//                .roles("MANAGER", "USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(password)
//                .roles("ADMIN", "MANAGER", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, sys, admin);
//    }

}
