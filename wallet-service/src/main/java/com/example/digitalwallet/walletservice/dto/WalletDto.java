package com.example.digitalwallet.walletservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {
    private Long id;
    private String publicAddress;
    private String userId;
    private BigDecimal balance = BigDecimal.ZERO;
}
