package com.example.digitalwallet.walletservice.controller;

import com.example.digitalwallet.walletservice.dto.WalletDto;
import com.example.digitalwallet.walletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallet-service/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;

    //create a new wallet
    @PostMapping("/create")
    public ResponseEntity<WalletDto> createWallet(@RequestParam String userId, @RequestParam String password) throws Exception {
        return walletService.createWallet(userId,password);
    }

    //get wallet details by public address
    @GetMapping("/details")
    public ResponseEntity<WalletDto> getWalletDetails(@RequestParam String publicAddress) throws Exception {
        return walletService.getWalletDetails(publicAddress);
    }

    //get balance of a wallet
    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam String publicAddress) throws Exception {
        return walletService.getBalance(publicAddress);
    }

    //deposit money to a wallet
    @PostMapping("/deposit")
    public ResponseEntity<WalletDto> deposit(@RequestParam String publicAddress, @RequestParam BigDecimal amount) throws Exception {
        return walletService.deposit(publicAddress,amount);
    }

    //withdraw money from a wallet
    @PostMapping("/withdraw")
    public ResponseEntity<WalletDto> withdraw(@RequestParam String publicAddress, @RequestParam BigDecimal amount) throws Exception {
        return walletService.withdraw(publicAddress,amount);
    }

    //transfer money from a wallet to another
    @PostMapping("/transfer")
    public ResponseEntity<WalletDto> transfer(@RequestParam String fromPublicAddress, @RequestParam String toPublicAddress, @RequestParam BigDecimal amount) throws Exception {
        return walletService.transfer(fromPublicAddress,toPublicAddress,amount);
    }

}
