package com.example.recom.controller;

import com.example.recom.dto.BuyItemDto;
import com.example.recom.entity.Item;
import com.example.recom.service.BuyItemService;
import com.example.recom.service.UserItemInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InteractionController {
    private final UserItemInteractionService userItemInteractionService;

    @GetMapping("/{itemId}")
    public Item getItem(@RequestParam Long userId, @PathVariable Long itemId) {
        return userItemInteractionService.getItemAndRecordInteraction(userId, itemId);
    }
}
