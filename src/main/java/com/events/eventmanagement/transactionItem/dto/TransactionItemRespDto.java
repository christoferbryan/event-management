package com.events.eventmanagement.transactionItem.dto;

import com.events.eventmanagement.transactionItem.entity.TransactionItem;
import lombok.Data;

@Data
public class TransactionItemRespDto {
    private String ticketName;
    private int quantity;
    private int ticketPrice;
    private int subtotal;

    public static TransactionItemRespDto toDto(TransactionItem trxItem){
        TransactionItemRespDto transactionItemRespDto = new TransactionItemRespDto();

        transactionItemRespDto.setTicketName(trxItem.getTicket().getName());
        transactionItemRespDto.setQuantity(trxItem.getQuantity());
        transactionItemRespDto.setTicketPrice(trxItem.getPrice());
        transactionItemRespDto.setSubtotal( trxItem.getQuantity() * trxItem.getPrice() );

        return transactionItemRespDto;
    }
}
