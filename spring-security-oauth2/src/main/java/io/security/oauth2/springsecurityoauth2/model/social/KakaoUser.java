package io.security.oauth2.springsecurityoauth2.model.social;

import io.security.oauth2.springsecurityoauth2.model.Attributes;
import io.security.oauth2.springsecurityoauth2.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class KakaoUser extends OAuth2ProviderUser {

    private Map<String, Object> otherAttributes;

    public KakaoUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(oAuth2User.getAttributes(), oAuth2User, clientRegistration);
        this.otherAttributes = attributes.getOtherAttributes();
    }

    @Override
    public String getId() {
        return (String) String.valueOf(getAttributes().get("id"));
    }

    @Override
    public String getUserName() {
        return (String) otherAttributes.get("nickname");
    }

}
