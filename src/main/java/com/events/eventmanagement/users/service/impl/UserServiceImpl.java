package com.events.eventmanagement.users.service.impl;

import com.events.eventmanagement.exceptions.InputException;
import com.events.eventmanagement.generator.ReferralCodeGenerator;
import com.events.eventmanagement.users.dto.RegisterRequestDto;
import com.events.eventmanagement.users.dto.RegisterResponseDto;
import com.events.eventmanagement.users.entity.User;
import com.events.eventmanagement.users.repository.UserRepository;
import com.events.eventmanagement.users.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @Transactional
    @Override
    public RegisterResponseDto register(RegisterRequestDto user){
        if( userRepository.findByEmail(user.getEmail()).isPresent() ) {
            throw new InputException("Email already exists");
        }

        User registeredUser = user.toEntity();
        registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));
        String code = ReferralCodeGenerator.generateReferralCode();
        registeredUser.setReferralCode(code);

        User savedUser = userRepository.save(registeredUser);
        return RegisterResponseDto.toDto(registeredUser);

    }


}
