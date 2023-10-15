package com.example.digitalwallet.userservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="keycloak")
@Getter
@Setter
public class KeycloakConfig {
    private String authServerUrl;
    private String realm;
    private String resource;
    private Credentials credentials;
    private String adminUsername;
    private String adminPassword;

    @Getter
    @Setter
    public static class Credentials {
        private String secret;
    }
}
