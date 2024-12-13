package com.group4.backend.service;

import java.util.List;

import com.group4.backend.model.Item;

public interface ItemService {

    Item createItem(Item item);

    // Get an item by its ID
    Item getItemById(Long id);

    // Get all items
    List<Item> getAllItems();

    // Update an existing item
    Item updateItem(Item updatedItem);

    // Delete an item by its ID
    boolean deleteItem(Long id);

    // get item by name
    Item getItemByName(String name);
}