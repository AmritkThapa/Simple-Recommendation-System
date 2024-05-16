package com.example.recom.service.Impl;

import com.example.recom.entity.UserItem;
import com.example.recom.repo.UserItemRepo;
import com.example.recom.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    public final UserItemRepo userItemRepo;

    @Override
    public List<UserItem> recommendItemsForUser(String userId) {

        // Fetch items for the user from the database
        List<UserItem> userItems = userItemRepo.findAll();

        //User-Item matrix
        Map<Long, Set<Long>> userItemMatrix = new HashMap<>();
        for (UserItem userItem : userItems) {
            userItemMatrix.computeIfAbsent(userItem.getUser().getId(), k -> new HashSet<>()).add(userItem.getItem().getId());
        }

        // Calculate cosine similarities
        Map<Long, Double> userSimilarities = new HashMap<>();
        Set<Long> targetUserItems = userItemMatrix.get(userId);

        // If targetUserItems is null, return an empty list of recommendations
        if (targetUserItems == null) {
            return new ArrayList<>();
        }

        for (Map.Entry<Long, Set<Long>> entry : userItemMatrix.entrySet()) {
            Long otherUserId = entry.getKey();
            Set<Long> otherUserItems = entry.getValue();

            int intersection = (int) targetUserItems.stream().filter(otherUserItems::contains).count();
            double similarity = intersection / (Math.sqrt(targetUserItems.size()) * Math.sqrt(otherUserItems.size()));
            userSimilarities.put(otherUserId, similarity);
        }

        // Generate recommendations
        List<UserItem> recommendations = new ArrayList<>();
        userSimilarities.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .limit(5)
                .forEach(entry -> {
                    Long similarUserId = entry.getKey();
                    userItems.stream()
                            .filter(userItem -> userItem.getUser().getId().equals(similarUserId) && !targetUserItems.contains(userItem.getItem().getId()))
                            .forEach(recommendations::add);
                });

        return recommendations;
    }
}
