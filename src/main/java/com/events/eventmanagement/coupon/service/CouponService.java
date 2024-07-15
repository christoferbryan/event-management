package com.events.eventmanagement.coupon.service;

import com.events.eventmanagement.coupon.dto.CouponDto;
import com.events.eventmanagement.event.entity.Event;

public interface CouponService {
    void  createCoupon(CouponDto couponDto, Event event);
    void createCoupon(CouponDto couponDto);
}
