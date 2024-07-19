package com.events.eventmanagement.coupon.repository;

import com.events.eventmanagement.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT c FROM Coupon c WHERE c.code = :code")
    Coupon findByCode(@Param("code") String code);

}
