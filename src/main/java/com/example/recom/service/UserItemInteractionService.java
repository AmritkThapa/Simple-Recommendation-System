package com.example.recom.service;

import com.example.recom.entity.Item;

public interface UserItemInteractionService {
    Item getItemAndRecordInteraction(Long userId, Long itemId);
}
