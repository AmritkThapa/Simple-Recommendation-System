package com.example.recom.service;

import com.example.recom.entity.Item;

import java.util.List;

public interface ItemService {
    public Item uploadItem(Item item);

    List<Item> uploadItems(List<Item> items);
}
