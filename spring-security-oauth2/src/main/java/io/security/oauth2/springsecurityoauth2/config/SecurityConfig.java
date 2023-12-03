package io.security.oauth2.springsecurityoauth2.config;

import io.security.oauth2.springsecurityoauth2.common.authority.CustomAuthorityMapper;
import io.security.oauth2.springsecurityoauth2.service.CustomOAuth2UserService;
import io.security.oauth2.springsecurityoauth2.service.CustomOidcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService  customOAuth2UserService;
    @Autowired
    private CustomOidcUserService customOidcUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/static/js/**", "/static/images/**", "/static/css/**", "/static/scss/**").permitAll()
                        .requestMatchers("/", "login").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .userInfoEndpoint(
                                userInfoEndpointConfig -> userInfoEndpointConfig
                                        .userService(customOAuth2UserService)
                                        .oidcUserService(customOidcUserService)
                        )
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/").permitAll()
                )
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/"))
                .build();
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper() {
        return new CustomAuthorityMapper();
    }

}
