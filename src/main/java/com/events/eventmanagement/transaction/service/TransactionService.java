package com.events.eventmanagement.transaction.service;

import com.events.eventmanagement.transaction.dto.TransactionRequestDto;
import com.events.eventmanagement.transaction.dto.TransactionResponseDto;

public interface TransactionService {
    TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto, String email);
}
