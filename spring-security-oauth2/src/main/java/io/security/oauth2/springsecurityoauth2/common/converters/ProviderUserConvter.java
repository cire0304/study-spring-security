package io.security.oauth2.springsecurityoauth2.common.converters;

import org.springframework.stereotype.Component;

@Component
public interface ProviderUserConvter<T, R> {

    R converter(T t);

}
