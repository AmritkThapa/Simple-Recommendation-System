package com.example.recom.service.Impl;

import com.example.recom.entity.User;
import com.example.recom.repo.UserRepo;
import com.example.recom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Override
    public User uploadUser(User user) {
        return userRepo.save(user);

    }

    @Override
    public List<User> uploadUsers(List<User> user) {
        return userRepo.saveAll(user);
    }
}
