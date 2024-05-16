package com.example.recom.repo;

import com.example.recom.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemRepo extends JpaRepository<UserItem, String> {
}
