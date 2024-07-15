package com.events.eventmanagement.referral.service;

import com.events.eventmanagement.referral.entity.Referral;
import com.events.eventmanagement.users.entity.User;

public interface ReferralService {
    Referral createReferral(User referrer, User referee);
}
