package com.example.recom.service.Impl;

import com.example.recom.entity.Item;
import com.example.recom.repo.ItemRepo;
import com.example.recom.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepo itemRepository;
    @Override
    public Item uploadItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> uploadItems(List<Item> items) {
        return itemRepository.saveAll(items);
    }
}
