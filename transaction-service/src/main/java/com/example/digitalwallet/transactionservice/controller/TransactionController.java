package com.example.digitalwallet.transactionservice.controller;

import com.example.digitalwallet.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction-service/transactions")
public class TransactionController {
    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService=transactionService;
    }
}
