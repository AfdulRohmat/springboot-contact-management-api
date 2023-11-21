package com.belajarspringboot.contactmanagamentapi.services;

import com.belajarspringboot.contactmanagamentapi.dtos.auth.LoginUserRequest;
import com.belajarspringboot.contactmanagamentapi.entities.User;
import com.belajarspringboot.contactmanagamentapi.dtos.user.RegisterUserRequest;
import com.belajarspringboot.contactmanagamentapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(@NotNull RegisterUserRequest request) {
        validationService.validate(request);

        // check if username already exist
        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exist!");
        }

        // encrypt password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Set value from request
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setName(request.getName());

        // save data to database via JPA
        userRepository.save(user);
    }


}
