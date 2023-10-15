package com.example.digitalwallet.userservice.service.impl;

import com.example.digitalwallet.userservice.configuration.KeycloakConfig;
import com.example.digitalwallet.userservice.service.AuthService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final Keycloak keycloak;
    private KeycloakConfig keycloakConfig;

    public AuthServiceImpl(KeycloakConfig keycloakConfig) {
        this.keycloakConfig = keycloakConfig;
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakConfig.getAuthServerUrl())
                .realm(keycloakConfig.getRealm())
                .clientId(keycloakConfig.getResource())
                .clientSecret(keycloakConfig.getCredentials().getSecret())
                .username(keycloakConfig.getAdminUsername())
                .password(keycloakConfig.getAdminPassword())
                .build();
    }

    @Override
    public AccessTokenResponse authenticate(String username, String password) {
        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", keycloakConfig.getCredentials().getSecret());

        Configuration authConfig = new Configuration(
                keycloakConfig.getAuthServerUrl(),
                keycloakConfig.getRealm(),
                keycloakConfig.getResource(),
                clientCredentials,
                null
        );

        AuthzClient authzClient = AuthzClient.create(authConfig);

        return authzClient.obtainAccessToken(username, password);
    }

    @Override
    public void initiatePasswordReset(String email) {
        RealmResource realmResource = keycloak.realm(keycloakConfig.getRealm());
        UsersResource usersResource = realmResource.users();

        List<UserRepresentation> users = usersResource.search(email);

        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);
            usersResource.get(user.getId()).executeActionsEmail(List.of("UPDATE_PASSWORD"));
        }
    }

    public void logout(String token) {
        keycloak.tokenManager().invalidate(token);
    }

    @Override
    public ResponseEntity<String> getNewAccessToken(String refreshToken) {
        return null;
    }
}
