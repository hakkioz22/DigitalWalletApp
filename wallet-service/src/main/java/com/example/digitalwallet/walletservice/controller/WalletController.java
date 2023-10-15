package com.example.digitalwallet.walletservice.controller;

import com.example.digitalwallet.walletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet-service/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;
}
