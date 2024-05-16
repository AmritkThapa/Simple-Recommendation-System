package com.example.recom.service;

import com.example.recom.entity.User;

import java.util.List;

public interface UserService {
    public User uploadUser(User user);

    List<User> uploadUsers(List<User> user);
}
