package io.security.oauth2.springsecurityoauth2.common.converters;

import io.security.oauth2.springsecurityoauth2.common.enums.OAuth2Config;
import io.security.oauth2.springsecurityoauth2.common.util.OAuth2Utils;
import io.security.oauth2.springsecurityoauth2.model.ProviderUser;
import io.security.oauth2.springsecurityoauth2.model.social.GoogleUser;
import io.security.oauth2.springsecurityoauth2.model.users.FormUser;
import io.security.oauth2.springsecurityoauth2.model.users.User;

public class UserDetailsProviderUserConverter implements ProviderUserConvter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (providerUserRequest.user() == null) {
            return null;
        }

        User user = providerUserRequest.user();

        return FormUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .authorities(user.getAuthorities())
                .provider("none")
                .build();
    }
}
