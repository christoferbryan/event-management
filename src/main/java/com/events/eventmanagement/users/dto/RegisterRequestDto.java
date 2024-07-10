package com.events.eventmanagement.users.dto;

import com.events.eventmanagement.users.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Role is required")
    private User.UserRole role;

    private String referralCode;

    public User toEntity(){
        User user = new User();
        user.setFullname(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        return user;
    }
}
