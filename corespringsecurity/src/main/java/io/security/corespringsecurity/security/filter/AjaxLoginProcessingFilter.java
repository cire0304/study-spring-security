package io.security.corespringsecurity.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.security.corespringsecurity.domain.AccountDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final HttpSecurity http;
    private ObjectMapper objectMapper = new ObjectMapper();

    public AjaxLoginProcessingFilter(HttpSecurity http) {
        super(new AntPathRequestMatcher("/api/login"));
        this.http = http;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // SessionAuthenticationStrategy 구현체를 얻어와서 설정한다
        SessionAuthenticationStrategy sessionAuthenticationStrategy = http.getSharedObject(SessionAuthenticationStrategy.class);
        if (sessionAuthenticationStrategy != null) {
            setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        }

//        AccountDto accountDto = objectMapper.readValue(request.getReader(), AccountDto.class);
        AccountDto accountDto = new AccountDto();
//        if (StringUtils.isEmpty(accountDto.getUsername()) || StringUtils.isEmpty(accountDto.getPassword())) {
//            throw new IllegalArgumentException("sdfasdf");
//        }

//        AjaxAuthenticationToken ajaxAuthenticationToken = new AjaxAuthenticationToken(accountDto.getUsername(), accountDto.getPassword());
        AjaxAuthenticationToken ajaxAuthenticationToken = new AjaxAuthenticationToken("1234", "1234");

        return getAuthenticationManager().authenticate(ajaxAuthenticationToken);
    }

}
