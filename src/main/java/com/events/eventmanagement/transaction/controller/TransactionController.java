package com.events.eventmanagement.transaction.controller;

import com.events.eventmanagement.auth.helper.Claims;
import com.events.eventmanagement.response.Response;
import com.events.eventmanagement.transaction.dto.TransactionRequestDto;
import com.events.eventmanagement.transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequestDto transactionRequestDto){
        var claims = Claims.getClaimsFromJwt();
        String email = (String) claims.get("sub");

        return Response.successResponse("Transaction successful", transactionService.createTransaction(transactionRequestDto, email));
    }
}
