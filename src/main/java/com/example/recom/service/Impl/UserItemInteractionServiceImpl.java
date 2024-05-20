package com.example.recom.service.Impl;

import com.example.recom.entity.Item;
import com.example.recom.entity.User;
import com.example.recom.entity.UserItem;
import com.example.recom.repo.ItemRepo;
import com.example.recom.repo.UserItemRepo;
import com.example.recom.repo.UserRepo;
import com.example.recom.service.UserItemInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserItemInteractionServiceImpl implements UserItemInteractionService {

    private final UserRepo userRepository;
    private final ItemRepo itemRepository;
    private final UserItemRepo userItemRepository;

    @Override
    public Item getItemAndRecordInteraction(Long userId, Long itemId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));

        UserItem interaction = new UserItem();
        interaction.setUser(user);
        interaction.setItem(item);
        userItemRepository.save(interaction);

        return item;
    }
}
