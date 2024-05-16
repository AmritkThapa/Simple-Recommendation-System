package com.example.recom.service;

import com.example.recom.entity.Item;
import com.example.recom.entity.UserItem;

import java.util.List;

public interface RecommendationService {
    List<UserItem> recommendItemsForUser(String userId);
}
