package io.security.oauth2.springsecurityoauth2.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

public interface ProviderUser {

    String getId();
    String getUserName();
    String getPassword();
    String getEmail();
    String getProvider();
    List<? extends GrantedAuthority> getAuthorities();
    Map<String, Object> getAttributes();

}
