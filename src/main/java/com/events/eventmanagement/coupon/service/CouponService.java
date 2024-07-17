package com.events.eventmanagement.coupon.service;

import com.events.eventmanagement.coupon.dto.CouponDto;
import com.events.eventmanagement.coupon.entity.Coupon;
import com.events.eventmanagement.event.entity.Event;

public interface CouponService {
    Coupon createCoupon(CouponDto couponDto, Event event);
    Coupon createCoupon(CouponDto couponDto);

    int useCoupon(Long couponId, int totalAmount);

    Coupon getCouponById(Long id);
}
