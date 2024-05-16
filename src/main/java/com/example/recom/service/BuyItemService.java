package com.example.recom.service;

import com.example.recom.dto.BuyItemDto;

import java.util.List;

public interface BuyItemService {

    void buyItem(BuyItemDto buyItemDto);
    void buyItem(List<BuyItemDto> buyItemDto);
}
