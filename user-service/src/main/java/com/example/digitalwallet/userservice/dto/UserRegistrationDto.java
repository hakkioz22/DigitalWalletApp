package com.example.digitalwallet.userservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isVerified;
}
