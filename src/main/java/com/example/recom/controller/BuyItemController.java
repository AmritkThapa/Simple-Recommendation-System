package com.example.recom.controller;

import com.example.recom.dto.BuyItemDto;
import com.example.recom.service.BuyItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BuyItemController {
    private final BuyItemService buyItemService;

    @PostMapping("/buyone")
    public void buyItem(@RequestBody BuyItemDto buyItemDto) {
        // Buy the item
        buyItemService.buyItem(buyItemDto);
    }

    @PostMapping("/buymore")
    public void buyItem(@RequestBody List<BuyItemDto> buyItemDto) {
        // Buy the item
        buyItemService.buyItem(buyItemDto);
    }
}
