package com.example.digitalwallet.walletservice.repository;

import com.example.digitalwallet.walletservice.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByPublicAddress(String publicAddress);
}
