package com.example.recom.controller;

import com.example.recom.entity.Item;
import com.example.recom.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/item")
    public Item uploadItem(@RequestBody Item item) {
        // Save the item to the database
        itemService.uploadItem(item);
        return item;
    }

    @PostMapping("/items")
    public List<Item> uploadItems(@RequestBody List<Item> items) {
        // Save the items to the database
        return itemService.uploadItems(items);
    }

}
