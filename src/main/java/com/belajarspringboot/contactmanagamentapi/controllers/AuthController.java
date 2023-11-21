package com.belajarspringboot.contactmanagamentapi.controllers;

import com.belajarspringboot.contactmanagamentapi.dtos.WebResponse;
import com.belajarspringboot.contactmanagamentapi.dtos.auth.LoginUserRequest;
import com.belajarspringboot.contactmanagamentapi.dtos.auth.TokenResponse;
import com.belajarspringboot.contactmanagamentapi.dtos.user.RegisterUserRequest;
import com.belajarspringboot.contactmanagamentapi.services.AuthService;
import com.belajarspringboot.contactmanagamentapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request) {
        TokenResponse tokenResponse = authService.login(request);
        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }
}



