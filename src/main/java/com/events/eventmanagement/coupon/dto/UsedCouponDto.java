package com.events.eventmanagement.coupon.dto;

import com.events.eventmanagement.coupon.entity.Coupon;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsedCouponDto {
    private String name;
    private int discount;
    private LocalDate expiryDate;

    public static UsedCouponDto toDto(Coupon coupon){
        UsedCouponDto usedCouponDto = new UsedCouponDto();

        usedCouponDto.setName(coupon.getName());
        usedCouponDto.setDiscount(coupon.getDiscount());
        usedCouponDto.setExpiryDate(coupon.getExpiredAt());

        return usedCouponDto;
    }
}
