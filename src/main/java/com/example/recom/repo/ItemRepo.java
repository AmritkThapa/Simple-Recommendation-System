package com.example.recom.repo;

import com.example.recom.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {

    List<Item> findAllById(Iterable<Long> ids);
}
