package com.example.recom.controller;

import com.example.recom.entity.Item;
import com.example.recom.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendController {
    private final RecommendationService recommendationService;

    @GetMapping("/user/{userId}")
    public List<Item> recommendItems(@PathVariable Long userId, @RequestParam(defaultValue = "5") int numRecommendations) {
        return recommendationService.recommendItems(userId, numRecommendations);
    }

}
