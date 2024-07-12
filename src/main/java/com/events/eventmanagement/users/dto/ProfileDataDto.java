package com.events.eventmanagement.users.dto;

import com.events.eventmanagement.users.entity.User;
import lombok.Data;
import org.springframework.context.annotation.Profile;

@Data
public class ProfileDataDto {
    private String name;
    private String email;
    private String profilePictureUrl;
    private String referralCode;
    private Integer points;

    public static ProfileDataDto toDto(User user){
        ProfileDataDto profileDataDto = new ProfileDataDto();
        profileDataDto.setName(user.getFullname());
        profileDataDto.setEmail(user.getEmail());
        profileDataDto.setProfilePictureUrl(user.getProfilePicture());
        profileDataDto.setReferralCode(user.getReferralCode());

        return profileDataDto;
    }
}
