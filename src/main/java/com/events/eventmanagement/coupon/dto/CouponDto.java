package com.events.eventmanagement.coupon.dto;

import com.events.eventmanagement.coupon.entity.Coupon;
import com.events.eventmanagement.referral.entity.Referral;
import lombok.Data;

import java.time.Instant;

@Data
public class CouponDto {
    private Boolean isReferral;
    private String name;
    private int discount;
    private int usageLimit;
    private Referral referral;
    private Instant expiryDate;

    public Coupon toEntity(){
        Coupon coupon = new Coupon();
        coupon.setIsReferral(isReferral);
        coupon.setName(name);
        coupon.setDiscount(discount);
        coupon.setUsageLimit(usageLimit);
        coupon.setExpiredAt(expiryDate);

        if(referral != null){
            coupon.setReferral(referral);
        }
        else {
            coupon.setReferral(null);
        }

        return coupon;
    }
}
