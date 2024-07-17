package com.events.eventmanagement.transaction.dto;

import com.events.eventmanagement.coupon.dto.UsedCouponDto;
import com.events.eventmanagement.transactionItem.dto.TransactionItemRespDto;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class TransactionResponseDto {
    private Long transactionId;
    private int totalAmount;
    private int usedPoints;
    private UsedCouponDto usedCoupon;
    private List<TransactionItemRespDto> trxItems;
    private Instant createdAt;

    public TransactionResponseDto(){
        createdAt = Instant.now();
    }
}
