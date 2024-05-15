package com.example.recom.recommender;

import com.example.recom.entity.Item;

import java.util.*;

public class Recommender {

    // Method to recommend items based on content
    public List<Item> recommendItems(List<Item> userItems) {
        // Create a map to store item categories and their frequency
        Map<String, Integer> categoryFrequency = new HashMap<>();

        // Count the frequency of each category among user items
        for (Item item : userItems) {
            String category = item.getCategory();
            categoryFrequency.put(category, categoryFrequency.getOrDefault(category, 0) + 1);
        }

        // Find the most frequent category among user items
        String mostFrequentCategory = null;
        int maxFrequency = 0;
        for (Map.Entry<String, Integer> entry : categoryFrequency.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostFrequentCategory = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        // Find items with similar categories to the most frequent category
        List<Item> recommendedItems = new ArrayList<>();
       // boolean mostFrequentCategoryAdded = false;
        for (Item item : userItems) {
            if (item.getCategory().equals(mostFrequentCategory)) {
                   // if (!mostFrequentCategoryAdded) {
                        recommendedItems.add(item);
                       // mostFrequentCategoryAdded = true;
                   // }
            }
            else {
                recommendedItems.add(item);
            }

        }

        // Display items with the same name only once
        Set<String> addedItemNames = new HashSet<>();
        List<Item> uniqueRecommendedItems = new ArrayList<>();
        for (Item item : recommendedItems) {
            if (addedItemNames.add(item.getName())) {
                uniqueRecommendedItems.add(item);
                //             Limit the recommended items to 4
                if (recommendedItems.size() >= 5) {
                    break;
                }
            }
        }
        //return recommendedItems;
        return uniqueRecommendedItems;
    }
}
