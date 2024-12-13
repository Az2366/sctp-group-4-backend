package com.group4.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.backend.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemName(String itemName);

}
