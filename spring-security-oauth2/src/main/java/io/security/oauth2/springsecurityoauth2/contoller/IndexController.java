package io.security.oauth2.springsecurityoauth2.contoller;

import io.security.oauth2.springsecurityoauth2.model.PrincipalUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, Authentication authentication, @AuthenticationPrincipal PrincipalUser principalUser) {


//        if (authentication != null) {
//
//            if (authentication instanceof OAuth2AuthenticationToken)
//
//            Map<String, Object> attributes = principalUser.getAttributes();
//            String name = (String) attributes.get("name");
//
//            if (oAuth2AutentictionToken.getAuthorizedClientRegistrationId().equals("naver")) {
//                Map<String, Object> response = (Map) attributes.get("response");
//                name = (String) response.get("name");
//            }
//            model.addAttribute("user", name);
//        }
        return "index";
    }

}
