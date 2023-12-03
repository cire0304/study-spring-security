package io.security.oauth2.springsecurityoauth2.common.converters;

import io.security.oauth2.springsecurityoauth2.model.ProviderUser;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DelegatingProviderUserConverter implements ProviderUserConvter<ProviderUserRequest, ProviderUser> {

    private List<ProviderUserConvter<ProviderUserRequest, ProviderUser>> converters;

    public DelegatingProviderUserConverter() {
        List<ProviderUserConvter<ProviderUserRequest, ProviderUser>> providerUserConvters =
                Arrays.asList(
                        new UserDetailsProviderUserConverter(),
                        new OAuth2GoogleProviderUserConverter(),
                        new OAuth2NaverProviderUserConverter(),
                        new OAuth2KakaoProviderUserConverter()
                );
        this.converters = Collections.unmodifiableList(providerUserConvters);
    }

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        Assert.notNull(providerUserRequest, "providerUserRequest cannot be null");

        for (ProviderUserConvter<ProviderUserRequest, ProviderUser> converter : converters) {
            ProviderUser providerUser = converter.converter(providerUserRequest);
            if (providerUser != null) return providerUser;
        }

        return null;
    }
}
