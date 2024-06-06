package com.sid.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
   /* @Bean
    OAuth2ClientRestTemplate restTemplate(final AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceOAuth2AuthorizedClientManager){
        return new OAuth2ClientRestTemplate(authorizedClientServiceOAuth2AuthorizedClientManager);
    }*/

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
