package com.example.digitalwallet.walletservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publicAddress;
    private String userId;
    private BigDecimal balance = BigDecimal.ZERO;
}
