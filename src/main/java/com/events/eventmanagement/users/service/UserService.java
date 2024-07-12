package com.events.eventmanagement.users.service;

import com.events.eventmanagement.users.dto.ProfileDataDto;
import com.events.eventmanagement.users.dto.RegisterRequestDto;
import com.events.eventmanagement.users.dto.RegisterResponseDto;
import com.events.eventmanagement.users.entity.User;

public interface UserService {
    RegisterResponseDto register(RegisterRequestDto user);
    ProfileDataDto getProfileData(String email);

    User getUserByEmail(String email);
}
