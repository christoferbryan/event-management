package com.events.eventmanagement.transaction.service.impl;

import com.events.eventmanagement.coupon.dto.UsedCouponDto;
import com.events.eventmanagement.coupon.entity.Coupon;
import com.events.eventmanagement.coupon.service.CouponService;
import com.events.eventmanagement.event.entity.Event;
import com.events.eventmanagement.event.service.EventService;
import com.events.eventmanagement.exceptions.InputException;
import com.events.eventmanagement.point.service.PointService;
import com.events.eventmanagement.ticket.entity.Ticket;
import com.events.eventmanagement.ticket.service.TicketService;
import com.events.eventmanagement.transaction.dto.TransactionRequestDto;
import com.events.eventmanagement.transaction.dto.TransactionResponseDto;
import com.events.eventmanagement.transaction.entity.Transaction;
import com.events.eventmanagement.transaction.repository.TransactionRepository;
import com.events.eventmanagement.transaction.service.TransactionService;
import com.events.eventmanagement.transactionItem.dto.TransactionItemDto;
import com.events.eventmanagement.transactionItem.dto.TransactionItemRespDto;
import com.events.eventmanagement.transactionItem.entity.TransactionItem;
import com.events.eventmanagement.transactionItem.service.TransactionItemService;
import com.events.eventmanagement.users.entity.User;
import com.events.eventmanagement.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TicketService ticketService;
    private final UserService userService;
    private final CouponService couponService;
    private final PointService pointService;
    private final EventService eventService;
    private final TransactionItemService transactionItemService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TicketService ticketService, UserService userService, CouponService couponService, PointService pointService, EventService eventService, TransactionItemService transactionItemService){
        this.transactionRepository = transactionRepository;
        this.ticketService = ticketService;
        this.userService = userService;
        this.couponService = couponService;
        this.pointService = pointService;
        this.eventService = eventService;
        this.transactionItemService = transactionItemService;
    }
    @Override
    @Transactional
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto, String email) {
        int totalAmount = 0;
        int pointsUsed = 0;
        Coupon coupon = new Coupon();
        User buyer = userService.getUserByEmail(email);
        Event event = eventService.getEventById(transactionRequestDto.getEventId());
        List<TransactionItem> transactionItems = new ArrayList<>();

        Transaction transaction = new Transaction();
        transaction.setBuyer(buyer);
        transaction.setEvent(event);

        for(TransactionItemDto trxItemDto : transactionRequestDto.getItems()){
            Ticket ticket = ticketService.getTicketById(trxItemDto.getTicketId());

            if( !(ticket.getEvent().equals(event)) ){
                throw new InputException("Ticket not valid for this event");
            }

            int quantity = trxItemDto.getQuantity();

            TransactionItem transactionItem = new TransactionItem();
            transactionItem.setTicket(ticket);
            transactionItem.setQuantity(quantity);
            transactionItem.setPrice(ticket.getPrice());

            transactionItems.add(transactionItem);

            ticketService.reduceSeats(ticket, quantity);

            int subtotal = ticket.getPrice() * quantity;
            totalAmount += subtotal;
        }

        if(transactionRequestDto.getCouponCode() != null){
            coupon = couponService.getCouponByCode(transactionRequestDto.getCouponCode());
            transaction.setCoupon(coupon);

            int discount = couponService.useCoupon(coupon.getId(), totalAmount, event);
            totalAmount = totalAmount - discount;
        }

        if(transactionRequestDto.getUsePoints()){
            pointsUsed = pointService.redeemUserPoints(buyer, totalAmount);
            totalAmount = totalAmount - pointsUsed;
        }

        transaction.setTotalAmount(totalAmount);
        transactionRepository.save(transaction);

        for(TransactionItem trxItem : transactionItems){
            trxItem.setTransaction(transaction);
            transactionItemService.addNewTransactionItem(trxItem);
        }

        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setTransactionId(transaction.getId());
        transactionResponseDto.setTotalAmount(totalAmount);
        transactionResponseDto.setUsedPoints(pointsUsed);

        UsedCouponDto usedCoupon = UsedCouponDto.toDto(coupon);
        transactionResponseDto.setUsedCoupon(usedCoupon);

        List<TransactionItemRespDto> itemRespDtos = transactionItems.stream().map(TransactionItemRespDto::toDto).toList();

        transactionResponseDto.setTrxItems(itemRespDtos);

        return transactionResponseDto;
    }
}
