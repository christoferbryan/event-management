package com.events.eventmanagement.users.controller;

import com.events.eventmanagement.auth.helper.Claims;
import com.events.eventmanagement.response.Response;
import com.events.eventmanagement.users.dto.ProfileDataDto;
import com.events.eventmanagement.users.dto.RegisterRequestDto;
import com.events.eventmanagement.users.service.UserService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Validated
@Log
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto){
        return Response.successResponse("User registered successfully", userService.register(registerRequestDto));
    }

    @GetMapping("/profile")
    public ResponseEntity<Response<ProfileDataDto>> getProfileData(){
        var claims = Claims.getClaimsFromJwt();
        String email = (String) claims.get("sub");
        log.info("Claims are: " + claims.toString());
        log.info("Profile data requested for user: " + email);
        return Response.successResponse("User data fetched successfully", userService.getProfileData(email));
    }
}
