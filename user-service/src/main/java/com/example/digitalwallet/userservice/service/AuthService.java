package com.example.digitalwallet.userservice.service;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AccessTokenResponse authenticate(String username, String password);
    void initiatePasswordReset(String email);
    ResponseEntity<String> getNewAccessToken(String refreshToken);
}
