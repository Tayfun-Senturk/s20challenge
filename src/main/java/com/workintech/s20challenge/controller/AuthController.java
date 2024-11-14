package com.workintech.s20challenge.controller;

import com.workintech.s20challenge.dto.RegisterResponse;
import com.workintech.s20challenge.dto.RegistrationMember;
import com.workintech.s20challenge.entity.Member;
import com.workintech.s20challenge.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegistrationMember registrationMember){
        Member registeredMember= authenticationService.register(registrationMember.email(),registrationMember.password(),registrationMember.auth());
        return new RegisterResponse(registeredMember.getEmail(),"kayıt başarılı bir şekilde gerçekleşti.");
    }
}

