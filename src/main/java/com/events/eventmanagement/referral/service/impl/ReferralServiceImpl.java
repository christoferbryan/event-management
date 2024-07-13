package com.events.eventmanagement.referral.service.impl;

import com.events.eventmanagement.referral.entity.Referral;
import com.events.eventmanagement.referral.repository.ReferralRepository;
import com.events.eventmanagement.referral.service.ReferralService;
import com.events.eventmanagement.users.entity.User;
import org.springframework.stereotype.Service;

@Service
public class ReferralServiceImpl implements ReferralService {
    private final ReferralRepository referralRepository;

    public ReferralServiceImpl(ReferralRepository referralRepository){
        this.referralRepository = referralRepository;
    }
    @Override
    public void createReferral(User referrer, User referee) {
        Referral referral = new Referral();
        referral.setReferrer(referrer);
        referral.setReferee(referee);

        referralRepository.save(referral);
    }
}
