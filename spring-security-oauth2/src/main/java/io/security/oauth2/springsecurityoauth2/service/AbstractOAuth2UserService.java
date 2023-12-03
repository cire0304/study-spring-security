package io.security.oauth2.springsecurityoauth2.service;

import io.security.oauth2.springsecurityoauth2.common.converters.ProviderUserConvter;
import io.security.oauth2.springsecurityoauth2.common.converters.ProviderUserRequest;
import io.security.oauth2.springsecurityoauth2.model.ProviderUser;
import io.security.oauth2.springsecurityoauth2.model.users.User;
import io.security.oauth2.springsecurityoauth2.model.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProviderUserConvter<ProviderUserRequest, ProviderUser> providerUserConvter;

    protected void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {
        User user = userRepository.findByUsername(providerUser.getUserName());

        if (user == null) {
            userService.register(userRequest.getClientRegistration().getRegistrationId(), providerUser);
        } else {
            System.out.println("user = " + user);
        }

    }
    protected ProviderUser providerUser(ProviderUserRequest providerUserRequest) {
        return providerUserConvter.converter(providerUserRequest);
    }

}
