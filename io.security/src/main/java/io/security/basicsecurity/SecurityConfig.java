package io.security.basicsecurity;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final class UserDetail

    @Bean
    public UserDetailsManager users() {

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}1111")
                .roles("USER")
                .build();

        UserDetails sys = User.builder()
                .username("sys")
                .password("{noop}1111")
                .roles("SYS")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}1111")
                .roles("ADMIN", "SYS", "USER")
                .build();

        return new InMemoryUserDetailsManager( user, sys, admin );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/admin/pay").hasRole("ADMIN")
                        .requestMatchers("/admin/**").access((new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('SYS')")))
                        .anyRequest().authenticated())
                .formLogin(login -> login
//                        .loginPage("/loginPage")
                                .defaultSuccessUrl("/")
                                .failureUrl("/login")
                                .usernameParameter("userId")
                                .passwordParameter("passwd")
                                .loginProcessingUrl("/login_proc")
                                .successHandler(((request, response, authentication) -> {
                                    RequestCache requestCache = new HttpSessionRequestCache();
                                    SavedRequest savedRequest = requestCache.getRequest(request, response);
                                    String redirectUrl = savedRequest.getRedirectUrl();
                                    response.sendRedirect(redirectUrl);
                                }))
                                .failureHandler(((request, response, exception) -> {
                                    System.out.println("exception" + exception.getMessage());
                                    response.sendRedirect("/login");
                                }))
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .addLogoutHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession();
                            session.invalidate();
                        })
                        .logoutSuccessHandler((request, response, authentication) ->
                                response.sendRedirect("login")
                        )
                        .deleteCookies("remember-me")
                )
                .rememberMe(remember -> remember
                        .rememberMeParameter("remember")
                        .tokenValiditySeconds(3600)
                        .alwaysRemember(true)
                        .userDetailsService(null)
                )
                .exceptionHandling(handler -> {
                    handler.accessDeniedHandler((request, response, accessDeniedException) ->
                            response.sendRedirect("/denied")
                    );
                    handler.authenticationEntryPoint((request, response, exception) ->
                        response.sendRedirect("/login")
                    );
                })
                .build();
    }

}
