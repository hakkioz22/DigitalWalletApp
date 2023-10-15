package com.example.digitalwallet.transactionservice.model;

import com.example.digitalwallet.transactionservice.enums.TransactionStatus;
import com.example.digitalwallet.transactionservice.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sourceWalletId;
    private Long destinationWalletId;
    private BigDecimal amount;
    private LocalDateTime transactionDate = LocalDateTime.now();
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;

}
