package com.events.eventmanagement.coupon.repository;

import com.events.eventmanagement.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT c FROM Coupon c WHERE c.code = :code")
    Optional<Coupon> findByCode(@Param("code") String code);

}
