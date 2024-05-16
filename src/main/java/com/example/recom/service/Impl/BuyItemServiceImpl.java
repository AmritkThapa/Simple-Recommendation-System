package com.example.recom.service.Impl;

import com.example.recom.dto.BuyItemDto;
import com.example.recom.entity.Item;
import com.example.recom.entity.User;
import com.example.recom.entity.UserItem;
import com.example.recom.repo.ItemRepo;
import com.example.recom.repo.UserItemRepo;
import com.example.recom.repo.UserRepo;
import com.example.recom.service.BuyItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyItemServiceImpl implements BuyItemService {
    public final ItemRepo itemRepo;
    public final UserRepo userRepo;
    public final UserItemRepo userItemRepo;

    @Override
    public void buyItem(BuyItemDto buyItemDto) {
        buySingleItem(buyItemDto);
    }

    @Transactional
    @Override
    public void buyItem(List<BuyItemDto> buyItemDto) {
        for (BuyItemDto item : buyItemDto) {
            buySingleItem(item);
        }
    }

    private void buySingleItem(BuyItemDto buyItemDto) {
        User user = userRepo.findById(buyItemDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Item item = itemRepo.findById(buyItemDto.getItemId()).orElseThrow(() -> new RuntimeException("Item not found"));

        UserItem userItem = new UserItem();
        userItem.setUser(user);
        userItem.setItem(item);
        userItemRepo.save(userItem);
    }
}
