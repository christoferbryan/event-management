package com.events.eventmanagement.users.service;

import com.events.eventmanagement.users.dto.RegisterRequestDto;
import com.events.eventmanagement.users.dto.RegisterResponseDto;

public interface UserService {
    RegisterResponseDto register(RegisterRequestDto user);

}
