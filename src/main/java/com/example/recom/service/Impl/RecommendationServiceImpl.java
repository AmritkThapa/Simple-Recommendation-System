package com.example.recom.service.Impl;

import com.example.recom.entity.Item;
import com.example.recom.recommender.Recommender;
import com.example.recom.repo.ItemRepo;
import com.example.recom.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService{

    public final ItemRepo itemRepository;
    //public final Recommender recommender;

    @Override
    public List<Item> recommendItemsForUser(String userId) {
        // Fetch items for the user from the database
        List<Item> userItems = itemRepository.findByUserId(userId);

        // Call content-based recommendation method
        return generateRecommendations(userItems);
    }

    private List<Item> generateRecommendations(List<Item> userItems) {
        // Content-based recommendation logic
        // You can use the ContentBasedRecommender class mentioned earlier
        Recommender recommender = new Recommender();
        return recommender.recommendItems(userItems);
    }
}
