package com.sid.ecommerce.config;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;*/
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;


public class OAuth2ClientRestTemplate extends RestTemplate {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    private final String CLIENT_REGISTRATION_ID="keycloak";

    //private final Authentication principal;

   /* public OAuth2ClientRestTemplate(final AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceAndManager){
       this.principal = createPrincipal();
        this.getInterceptors().add((request, body, execution) -> {
            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(CLIENT_REGISTRATION_ID)
                    .principal(this.principal)
                    .build();
            OAuth2AuthorizedClient authorizedClient = authorizedClientServiceAndManager.authorize(authorizeRequest);
            final var token = Objects.requireNonNull(authorizedClient).getAccessToken().getTokenValue();
            request.getHeaders().put(AUTHORIZATION, Collections.singletonList(BEARER.concat(token)));
            return execution.execute(request, body);
        });
    }

    private Authentication createPrincipal() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.emptySet();
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return this;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            }

            @Override
            public String getName() {
                return null; //clientRegistration.getClientId();
            }
        };
    }*/

}
