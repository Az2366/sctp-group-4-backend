package com.group4.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group4.backend.exception.ItemNotFoundException;
import com.group4.backend.model.Item;
import com.group4.backend.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
public class ItemServiceImpl {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Create a new item
    @Transactional
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // Get an item by its ID
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    // Get all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Update an existing item
    @Transactional
    public Item updateItem(Long id, Item updatedItem) {
        return itemRepository.findById(id).map(item -> {
            item.setItemName(updatedItem.getItemName());
            item.setItemDescription(updatedItem.getItemDescription());
            item.setPrice(updatedItem.getPrice());
            return itemRepository.save(item);
        }).orElseThrow(() -> new ItemNotFoundException(id));
    }

    // Delete an item by its ID
    @Transactional
    public boolean deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true; // Successfully deleted
        }
        return false;
    }
}
