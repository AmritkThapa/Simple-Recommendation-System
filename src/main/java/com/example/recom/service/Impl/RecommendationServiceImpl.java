package com.example.recom.service.Impl;

import com.example.recom.entity.Item;
import com.example.recom.entity.UserItem;
import com.example.recom.recommender.CosineSimilarity;
import com.example.recom.repo.ItemRepo;
import com.example.recom.repo.UserItemRepo;
import com.example.recom.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    public final UserItemRepo userItemRepo;
    public final ItemRepo itemRepository;

    @Override
    public List<Item> recommendItems(Long userId, int numRecommendations) {
        // Step 1: Get all item IDs
        List<Long> allItemIds = getAllItemIds();
        int itemCount = allItemIds.size();

        // Step 2: Create user vectors with binary values (interaction/no interaction)
        Map<Long, double[]> userVectors = new HashMap<>();
        List<Long> allUserIds = userItemRepo.findAllUserIds();

        for (Long uid : allUserIds) {
            double[] vector = new double[itemCount];
            Arrays.fill(vector, 0);
            List<UserItem> userItems = userItemRepo.findByUserId(uid);
            for (UserItem ui : userItems) {
                int index = allItemIds.indexOf(ui.getItem().getId());
                vector[index] = 1; // Mark as interacted
            }
            userVectors.put(uid, vector);
        }

        // Step 3: Calculate cosine similarity with other users
        double[] targetUserVector = userVectors.get(userId);
        Map<Long, Double> similarityScores = new HashMap<>();

        for (Map.Entry<Long, double[]> entry : userVectors.entrySet()) {
            Long uid = entry.getKey();
            if (!uid.equals(userId)) {
                double similarity = CosineSimilarity.calculate(targetUserVector, entry.getValue());
                similarityScores.put(uid, similarity);
            }
        }

        // Step 4: Find the most similar users
        List<Long> similarUserIds = similarityScores.entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .limit(numRecommendations)
                .map(Map.Entry::getKey)
                .toList();

        // Step 5: Recommend items that similar users interacted with but the target user hasn't
        Set<Long> targetUserItemIds = userItemRepo.findByUserId(userId).stream()
                .map(userItem -> userItem.getItem().getId())
                .collect(Collectors.toSet());

        List<Long> recommendedItemIds = new ArrayList<>();
        for (Long similarUserId : similarUserIds) {
            List<UserItem> similarUserItems = userItemRepo.findByUserId(similarUserId);
            for (UserItem userItem : similarUserItems) {
                if (!targetUserItemIds.contains(userItem.getItem().getId())) {
                    recommendedItemIds.add(userItem.getItem().getId());
                }
            }
        }

        // Step 6: Randomize and limit the recommended items
        Collections.shuffle(recommendedItemIds);
        List<Long> randomizedRecommendedItemIds = recommendedItemIds.stream().limit(numRecommendations).collect(Collectors.toList());

        // Step 7: Fetch the recommended items from the repository
        return new ArrayList<>(itemRepository.findAllById(randomizedRecommendedItemIds));
    }

    public List<Long> getAllItemIds() {
        return itemRepository.findAll().stream().map(Item::getId).collect(Collectors.toList());
    }

}
