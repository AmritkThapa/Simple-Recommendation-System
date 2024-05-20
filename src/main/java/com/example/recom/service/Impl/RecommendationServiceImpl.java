package com.example.recom.service.Impl;

import com.example.recom.entity.Item;
import com.example.recom.entity.User;
import com.example.recom.entity.UserItem;
import com.example.recom.recommender.CosineSimilarity;
import com.example.recom.repo.ItemRepo;
import com.example.recom.repo.UserItemRepo;
import com.example.recom.repo.UserRepo;
import com.example.recom.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    public final UserItemRepo userItemRepository;
    public final ItemRepo itemRepository;
    public final UserRepo userRepository;

    @Override
    public List<Item> recommendItems(Long userId, int numRecommendations) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Get all items
        List<Item> allItems = itemRepository.findAll();

        // Calculate similarity scores
        Map<Item, Double> similarityScores = new HashMap<>();
        for (Item item : allItems) {
            double similarityScore = calculateSimilarity(user, item);
            similarityScores.put(item, similarityScore);
        }

        // Sort items by similarity score
        List<Item> sortedItems = similarityScores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Limit the number of recommendations
        return sortedItems.stream().limit(numRecommendations).collect(Collectors.toList());
    }

    private double calculateSimilarity(User user, Item item) {
        // Get user-item interactions
        List<UserItem> userItems = userItemRepository.findByUser(user);

        // Calculate content-based similarity (e.g., using cosine similarity)
        double[] vectorA = getUserVector(user, userItems); // Vector representation of the user
        double[] vectorB = getItemVector(item); // Vector representation of the item
        double contentBasedSimilarity = CosineSimilarity.calculate(vectorA, vectorB);

        // Calculate collaborative filtering similarity
        double collaborativeFilteringScore = calculateCollaborativeFilteringScore(user, item, userItems);

        // Combine the scores (e.g., using a weighted average)
        return contentBasedSimilarity * 0.5 + collaborativeFilteringScore * 0.5;
    }

    private double[] getUserVector(User user, List<UserItem> userItems) {
        // Get all unique items in the system
        List<Item> allItems = itemRepository.findAll();

        // Create a map where each key is an item and the value is the number of times the user has interacted with that item
        Map<Item, Long> itemCountMap = userItems.stream()
                .collect(Collectors.groupingBy(UserItem::getItem, Collectors.counting()));

        // Create a vector where each element represents a unique item
        double[] userVector = new double[allItems.size()];
        for (int i = 0; i < allItems.size(); i++) {
            Item item = allItems.get(i);
            userVector[i] = itemCountMap.getOrDefault(item, 0L);
        }

        return userVector;
    }

    private double[] getItemVector(Item item) {
        // Get all unique users in the system
        List<User> allUsers = userRepository.findAll();

        // Create a map where each key is a user and the value is the number of times the user has interacted with the item
        Map<User, Long> userCountMap = userItemRepository.findByItem(item).stream()
                .collect(Collectors.groupingBy(UserItem::getUser, Collectors.counting()));

        // Create a vector where each element represents a unique user
        double[] itemVector = new double[allUsers.size()];
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            itemVector[i] = userCountMap.getOrDefault(user, 0L);
        }

        return itemVector;
    }

    private double calculateCollaborativeFilteringScore(User user, Item item, List<UserItem> userItems) {
        // Calculate the score based on user-item interactions
        double score = 0.0;
        for (UserItem userItem : userItems) {
            if (userItem.getItem().equals(item)) {
                score += userItem.isPurchased() ? 0.5 : 1.0;
            }
        }
        return score;
    }
}
