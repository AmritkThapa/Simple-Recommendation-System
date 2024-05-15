package com.example.recom.service;

import com.example.recom.entity.Item;

import java.util.List;

public interface RecommendationService {

    List<Item> recommendItemsForUser(String userId);
}
