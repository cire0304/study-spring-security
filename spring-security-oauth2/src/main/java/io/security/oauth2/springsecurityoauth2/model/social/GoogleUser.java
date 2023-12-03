package io.security.oauth2.springsecurityoauth2.model.social;

import io.security.oauth2.springsecurityoauth2.model.Attributes;
import io.security.oauth2.springsecurityoauth2.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class GoogleUser extends OAuth2ProviderUser {
    public GoogleUser(Attributes mainAttribute, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(mainAttribute.getMainAttributes(), oAuth2User, clientRegistration);
    }
    @Override
    public String getId() {
        return (String)getAttributes().get("sub");
    }

    @Override
    public String getUserName() {
        return (String)getAttributes().get("sub");
    }

}
