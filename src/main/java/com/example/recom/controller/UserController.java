package com.example.recom.controller;

import com.example.recom.entity.User;
import com.example.recom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/user")
    public User uploadUser(@RequestBody User user) {
        // Save the user to the database
        userService.uploadUser(user);
        return user;
    }
}
