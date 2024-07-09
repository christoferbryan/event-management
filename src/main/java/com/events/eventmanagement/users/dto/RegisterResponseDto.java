package com.events.eventmanagement.users.dto;

import com.events.eventmanagement.users.entity.User;
import lombok.Data;

@Data
public class RegisterResponseDto {
    private String name;
    private String email;

    public static RegisterResponseDto toDto(User user){
        RegisterResponseDto responseDto = new RegisterResponseDto();
        responseDto.setName(user.getUsername());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }
}
