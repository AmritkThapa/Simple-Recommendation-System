package com.example.recom.repo;

import com.example.recom.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserItemRepo extends JpaRepository<UserItem, Long> {
    List<UserItem> findByUserId(Long userId);

    @Query("SELECT DISTINCT ui.user.id FROM UserItem ui")
    List<Long> findAllUserIds();
}
