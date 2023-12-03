package io.security.oauth2.springsecurityoauth2.service;

import io.security.oauth2.springsecurityoauth2.common.converters.ProviderUserRequest;
import io.security.oauth2.springsecurityoauth2.model.PrincipalUser;
import io.security.oauth2.springsecurityoauth2.model.ProviderUser;
import io.security.oauth2.springsecurityoauth2.model.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService extends AbstractOAuth2UserService implements UserDetailsService {

//    private final userRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        User user = userRepository.findByUsername(username);
            User user = null;
        if (user == null) {
            user = User.builder()
                    .id("1")
                    .username("user1")
                    .password("{noop}1234")
                    .authorities(AuthorityUtils.createAuthorityList("ROLE_USER"))
                    .email("user@a.com")
                    .build();
        }

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(user);
        ProviderUser providerUser = super.providerUser(providerUserRequest);

        return new PrincipalUser(providerUser);
    }
}
