package com.events.eventmanagement.transaction.dto;

import com.events.eventmanagement.transactionItem.dto.TransactionItemDto;
import lombok.Data;

import java.util.List;

@Data
public class TransactionRequestDto {
    private Long eventId;
    private String couponCode;
    private Boolean usePoints;
    private List<TransactionItemDto> items;
}
