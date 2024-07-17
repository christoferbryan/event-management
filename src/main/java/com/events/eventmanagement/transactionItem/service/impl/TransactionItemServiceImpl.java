package com.events.eventmanagement.transactionItem.service.impl;

import com.events.eventmanagement.transaction.service.impl.TransactionServiceImpl;
import com.events.eventmanagement.transactionItem.entity.TransactionItem;
import com.events.eventmanagement.transactionItem.repository.TransactionItemRepository;
import com.events.eventmanagement.transactionItem.service.TransactionItemService;
import org.springframework.stereotype.Service;

@Service
public class TransactionItemServiceImpl implements TransactionItemService {

    private final TransactionItemRepository transactionItemRepository;

    public TransactionItemServiceImpl(TransactionItemRepository transactionItemRepository){
        this.transactionItemRepository = transactionItemRepository;
    }
    @Override
    public void addNewTransactionItem(TransactionItem transactionItem) {
        transactionItemRepository.save(transactionItem);
    }
}

