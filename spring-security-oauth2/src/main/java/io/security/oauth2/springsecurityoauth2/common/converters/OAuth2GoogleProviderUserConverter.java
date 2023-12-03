package io.security.oauth2.springsecurityoauth2.common.converters;

import io.security.oauth2.springsecurityoauth2.common.enums.OAuth2Config;
import io.security.oauth2.springsecurityoauth2.common.util.OAuth2Utils;
import io.security.oauth2.springsecurityoauth2.model.ProviderUser;
import io.security.oauth2.springsecurityoauth2.model.social.GoogleUser;

public class OAuth2GoogleProviderUserConverter implements ProviderUserConvter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.GOOGLE.getSocialName())) {
            return null;
        }
        return new GoogleUser(
                OAuth2Utils.getMainAttribute(providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
        );
    }
}
