package com.events.eventmanagement.referral.service;

import com.events.eventmanagement.users.entity.User;

public interface ReferralService {
    void createReferral(User referrer, User referee);
}
