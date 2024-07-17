package com.events.eventmanagement.transactionItem.repository;

import com.events.eventmanagement.transactionItem.entity.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
}
