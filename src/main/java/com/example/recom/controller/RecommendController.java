package com.example.recom.controller;

import com.example.recom.entity.UserItem;
import com.example.recom.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendController {
    private final RecommendationService recommendationService;

    @GetMapping("/user/{userId}")
    public List<UserItem> recommendItemsForUser(@PathVariable String userId) {
        // Call service method to get recommendations for the user
        return recommendationService.recommendItemsForUser(userId);
    }

}
