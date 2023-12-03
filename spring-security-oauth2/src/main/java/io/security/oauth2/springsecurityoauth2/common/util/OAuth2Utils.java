package io.security.oauth2.springsecurityoauth2.common.util;

import io.security.oauth2.springsecurityoauth2.model.Attributes;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class OAuth2Utils {

    public static Attributes getMainAttribute(OAuth2User oAuth2User) {
        return Attributes.builder()
                .mainAttributes(oAuth2User.getAttributes())
                .build();
    }

    public static Attributes getSubAttribute(OAuth2User oAuth2User, String subAttributesKey) {
        Map<String, Object> subAttributes = (Map<String, Object>) oAuth2User.getAttributes().get(subAttributesKey);
        return Attributes.builder()
                .subAttributes(subAttributes)
                .build();
    }

    public static Attributes getOtherAttribute(OAuth2User oAuth2User, String subAttributesKey, String otherAttributesKey) {
        Map<String, Object> subAttributes = (Map<String, Object>) oAuth2User.getAttributes().get(subAttributesKey);
        Map<String, Object> otherAttributes = (Map<String, Object>) subAttributes.get(otherAttributesKey);
        return Attributes.builder()
                .subAttributes(subAttributes)
                .otherAttributes(otherAttributes)
                .build();
    }


}
