package com.events.eventmanagement.coupon.service.impl;

import com.events.eventmanagement.coupon.dto.CouponDto;
import com.events.eventmanagement.coupon.entity.Coupon;
import com.events.eventmanagement.coupon.repository.CouponRepository;
import com.events.eventmanagement.coupon.service.CouponService;
import com.events.eventmanagement.event.entity.Event;
import com.events.eventmanagement.exceptions.DataNotFoundException;
import com.events.eventmanagement.exceptions.InputException;
import com.events.eventmanagement.referral.entity.Referral;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
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
            couponDto.setExpiryDate(LocalDate.now().plusMonths(3));
        }

        Coupon coupon = couponDto.toEntity();
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }

    @Override
    public Coupon createCoupon(CouponDto couponDto) {
        return createCoupon(couponDto, null);
    }

    @Override
    public int useCoupon(Long couponId, int totalAmount) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new DataNotFoundException("Coupon not found"));

        if(coupon.getExpiredAt().isBefore(LocalDate.now())){
            throw new InputException("Coupon is expired");
        }

        if(coupon.getIsReferral()){
            Referral referral = coupon.getReferral();
            if( !referral.getIsClaimed() ){
                referral.setIsClaimed(true);
            }
            else {
                throw new InputException("Referral coupon has already been claimed");
            }
        }

        if(coupon.getUsageLimit() == 0){
            throw new InputException("Coupon reaches usage limit");
        }

        int usageLimit = coupon.getUsageLimit() - 1;
        coupon.setUsageLimit(usageLimit);

        return totalAmount * (coupon.getDiscount() / 100);
    }

    @Override
    public Coupon getCouponById(Long id) {
        return couponRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Coupon not found"));
    }
}
