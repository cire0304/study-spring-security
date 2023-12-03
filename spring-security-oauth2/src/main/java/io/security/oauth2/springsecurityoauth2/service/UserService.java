package io.security.oauth2.springsecurityoauth2.service;

import io.security.oauth2.springsecurityoauth2.model.ProviderUser;
import io.security.oauth2.springsecurityoauth2.model.users.User;
import io.security.oauth2.springsecurityoauth2.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(String registrationId, ProviderUser providerUser) {
        User user = User.builder()
                .registrationId(registrationId)
                .id(providerUser.getId())
                .username(providerUser.getUserName())
                .email(providerUser.getEmail())
                .authorities(providerUser.getAuthorities())
                .provider(providerUser.getProvider())
                .build();

        userRepository.register(user);
    }

}
