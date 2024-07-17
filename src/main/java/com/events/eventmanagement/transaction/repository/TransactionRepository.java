package com.events.eventmanagement.transaction.repository;

import com.events.eventmanagement.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
