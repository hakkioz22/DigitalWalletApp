package com.example.digitalwallet.walletservice.service;

import com.example.digitalwallet.walletservice.dto.WalletDto;
import com.example.digitalwallet.walletservice.model.Wallet;
import com.example.digitalwallet.walletservice.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.math.BigDecimal;

@Service
public class WalletService {

    //Constructor Dependency Injection
    private final WalletRepository walletRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    //create a new wallet
    public ResponseEntity<WalletDto> createWallet(String userId,String password) throws Exception {
        Wallet newWallet = Wallet.builder()
                .balance(BigDecimal.valueOf(0))
                .userId(userId)
                .publicAddress("")
                .build();

        //New ECKeyPair object --> generate a new public and private key pair
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();

        //create a new erc20 wallet
        String walletFileName = WalletUtils.generateWalletFile(password, ecKeyPair, new File("./"), false);

        //retrieve the infos of the wallet
        Credentials credentials = WalletUtils.loadCredentials(password, "./" + walletFileName);

        //retrieve public address
        String publicAddress = credentials.getAddress();

        newWallet.setPublicAddress(publicAddress);

        return new ResponseEntity<>(modelMapper.map(walletRepository.save(newWallet), WalletDto.class), org.springframework.http.HttpStatus.CREATED);
    }
    //get balance of a wallet
    public ResponseEntity<BigDecimal> getBalance(String publicAddress) {
        Wallet foundWallet = walletRepository.findByPublicAddress(publicAddress);
        return new ResponseEntity<>(foundWallet.getBalance(), HttpStatus.OK);
    }
    //deposit money to a wallet
    public ResponseEntity<WalletDto> deposit(String publicAddress, BigDecimal amount) {
        Wallet foundWallet = walletRepository.findByPublicAddress(publicAddress);
        foundWallet.setBalance(foundWallet.getBalance().add(amount));
        return new ResponseEntity<>(modelMapper.map(walletRepository.save(foundWallet), WalletDto.class), HttpStatus.OK);
    }
    //withdraw money from a wallet
    public ResponseEntity<WalletDto> withdraw(String publicAddress, BigDecimal amount) {
        Wallet foundWallet = walletRepository.findByPublicAddress(publicAddress);
        foundWallet.setBalance(foundWallet.getBalance().subtract(amount));
        return new ResponseEntity<>(modelMapper.map(walletRepository.save(foundWallet), WalletDto.class), HttpStatus.OK);
    }
    //transfer money from a wallet to another
    public ResponseEntity<WalletDto> transfer(String fromPublicAddress, String toPublicAddress, BigDecimal amount) {
        Wallet fromWallet = walletRepository.findByPublicAddress(fromPublicAddress);
        Wallet toWallet = walletRepository.findByPublicAddress(toPublicAddress);
        fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
        toWallet.setBalance(toWallet.getBalance().add(amount));
        return new ResponseEntity<>(modelMapper.map(walletRepository.save(fromWallet), WalletDto.class), HttpStatus.OK);
    }
    //get wallet details by public address
    public ResponseEntity<WalletDto> getWalletDetails(String publicAddress) {
        Wallet foundWallet = walletRepository.findByPublicAddress(publicAddress);
        return new ResponseEntity<>(modelMapper.map(foundWallet, WalletDto.class), HttpStatus.OK);
    }
}
