package com.events.eventmanagement.users.dto;

import com.events.eventmanagement.coupon.entity.Coupon;
import com.events.eventmanagement.users.entity.User;
import lombok.Data;

@Data
public class RegisterResponseDto {
    private String name;
    private String email;
    private String referralCouponCode;

    public static RegisterResponseDto toDto(User user, Coupon referralCoupon){
        RegisterResponseDto responseDto = new RegisterResponseDto();
        responseDto.setName(user.getFullname());
        responseDto.setEmail(user.getEmail());
        responseDto.setReferralCouponCode(referralCoupon.getCode());
        return responseDto;
    }

    public static RegisterResponseDto toDto(User user){
        RegisterResponseDto responseDto = new RegisterResponseDto();
        responseDto.setName(user.getFullname());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }
}
