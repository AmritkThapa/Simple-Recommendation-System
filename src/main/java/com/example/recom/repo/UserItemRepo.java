package com.example.recom.repo;

import com.example.recom.entity.Item;
import com.example.recom.entity.User;
import com.example.recom.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserItemRepo extends JpaRepository<UserItem, Long> {
    List<UserItem> findByUserId(Long userId);

    @Query("SELECT DISTINCT ui.user.id FROM UserItem ui")
    List<Long> findAllUserIds();

    boolean existsByUserIdAndItemId(Long userId, Long itemId);

    List<UserItem> findByItemId(Long itemId);

    List<UserItem> findByUserIdAndIsPurchased(Long similarUserId, boolean b);

    List<UserItem> findByUser(User user);

    List<UserItem> findByItem(Item item);
}
