package com.events.eventmanagement.coupon.repository;

import com.events.eventmanagement.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
