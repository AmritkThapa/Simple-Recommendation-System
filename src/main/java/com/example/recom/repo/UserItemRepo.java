package com.example.recom.repo;

import com.example.recom.entity.Item;
import com.example.recom.entity.User;
import com.example.recom.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserItemRepo extends JpaRepository<UserItem, Long> {
    List<UserItem> findByUser(User user);

    List<UserItem> findByItem(Item item);
}
