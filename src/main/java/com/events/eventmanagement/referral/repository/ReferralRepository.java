package com.events.eventmanagement.referral.repository;

import com.events.eventmanagement.referral.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferralRepository extends JpaRepository<Referral, Long> {
}
