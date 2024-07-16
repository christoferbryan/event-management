package com.events.eventmanagement.users.service.impl;

import com.events.eventmanagement.coupon.dto.CouponDto;
import com.events.eventmanagement.coupon.service.CouponService;
import com.events.eventmanagement.exceptions.DataNotFoundException;
import com.events.eventmanagement.exceptions.InputException;
import com.events.eventmanagement.generator.ReferralCodeGenerator;
import com.events.eventmanagement.point.service.PointService;
import com.events.eventmanagement.referral.entity.Referral;
import com.events.eventmanagement.referral.service.ReferralService;
import com.events.eventmanagement.users.dto.ProfileDataDto;
import com.events.eventmanagement.users.dto.RegisterRequestDto;
import com.events.eventmanagement.users.dto.RegisterResponseDto;
import com.events.eventmanagement.users.entity.User;
import com.events.eventmanagement.users.repository.UserRepository;
import com.events.eventmanagement.users.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PointService pointService;
    private final ReferralService referralService;
    private final CouponService couponService;
    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PointService pointService, ReferralService referralService, CouponService couponService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.pointService = pointService;
        this.referralService = referralService;
        this.couponService = couponService;
    }

    @Transactional
    @Override
    public RegisterResponseDto register(RegisterRequestDto user){
        if( userRepository.findByEmail(user.getEmail()).isPresent() ) {
            throw new InputException("Email already exists");
        }

        User registeredUser = user.toEntity();
        registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));

        if(registeredUser.getRole() == User.UserRole.CUSTOMER){
            String code = ReferralCodeGenerator.generateReferralCode();
            registeredUser.setReferralCode(code);
        }
        else {
            registeredUser.setReferralCode(null);
        }

        userRepository.save(registeredUser);

        if(!user.getReferralCode().isEmpty() && registeredUser.getRole() == User.UserRole.CUSTOMER){
            User referrer = userRepository.findByReferralCode(user.getReferralCode()).orElseThrow(() -> new DataNotFoundException("User not found"));
            Referral referral = referralService.createReferral(referrer, registeredUser);
            pointService.addPoints(referrer, 10000);

            CouponDto couponDto = new CouponDto();
            couponDto.setIsReferral(true);
            couponDto.setReferral(referral);
            couponService.createCoupon(couponDto);
        }

        return RegisterResponseDto.toDto(registeredUser);
    }

    @Override
    public ProfileDataDto getProfileData(String email){
        User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User not found"));
        ProfileDataDto profileDto = ProfileDataDto.toDto(currentUser);
        profileDto.setPoints(pointService.getActiveUserPoints(currentUser.getId()));

        return profileDto;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User not found"));
    }
}
