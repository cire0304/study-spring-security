package io.security.oauth2.springsecurityoauth2;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomSecurityConfigurer extends AbstractHttpConfigurer<CustomSecurityConfigurer, HttpSecurity> {

    private boolean flag;

    @Override
    public void init(HttpSecurity builder) throws Exception {

    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
    }

    public CustomSecurityConfigurer flag(boolean value) {
        this.flag = value;
        return this;
    }



}
