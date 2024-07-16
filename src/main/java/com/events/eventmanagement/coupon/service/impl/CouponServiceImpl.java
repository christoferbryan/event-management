package com.events.eventmanagement.coupon.service.impl;

import com.events.eventmanagement.coupon.dto.CouponDto;
import com.events.eventmanagement.coupon.entity.Coupon;
import com.events.eventmanagement.coupon.repository.CouponRepository;
import com.events.eventmanagement.coupon.service.CouponService;
import com.events.eventmanagement.event.entity.Event;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository){
        this.couponRepository = couponRepository;
    }
    @Override
    public Coupon createCoupon(CouponDto couponDto, Event event) {
        if(couponDto.getIsReferral()){
            couponDto.setDiscount(10);
            couponDto.setName("Referral Coupon 10% Off");
            couponDto.setUsageLimit(1);
            couponDto.setExpiryDate(Instant.now().plus(90, ChronoUnit.DAYS));
        }

        Coupon coupon = couponDto.toEntity();
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }

    @Override
    public Coupon createCoupon(CouponDto couponDto) {
        return createCoupon(couponDto, null);
    }
}
