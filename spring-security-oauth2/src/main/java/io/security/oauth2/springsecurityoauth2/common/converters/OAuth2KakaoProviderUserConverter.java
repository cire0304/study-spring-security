package io.security.oauth2.springsecurityoauth2.common.converters;

import io.security.oauth2.springsecurityoauth2.common.enums.OAuth2Config;
import io.security.oauth2.springsecurityoauth2.common.util.OAuth2Utils;
import io.security.oauth2.springsecurityoauth2.model.ProviderUser;
import io.security.oauth2.springsecurityoauth2.model.social.KakaoUser;

public class OAuth2KakaoProviderUserConverter implements ProviderUserConvter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.KAKAO.getSocialName())) {
            return null;
        }
        return new KakaoUser(
                OAuth2Utils.getOtherAttribute(
                providerUserRequest.oAuth2User(), "kakao_account", "profile"),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
                );
    }
}
