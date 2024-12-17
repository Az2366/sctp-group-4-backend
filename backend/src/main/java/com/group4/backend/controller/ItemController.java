package com.group4.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.backend.model.Item;
import com.group4.backend.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {
    
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new item", description = "Register a new item based on the provided details")
    public ResponseEntity<Item> register(@Valid @RequestBody Item item) {
        return new ResponseEntity<>(itemService.createItem(item), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get item by ID", description = "Fetches item details by their unique ID")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle 404 if item not found
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Update an existing item", description = "Updates the item details based on the provided ID and data")
    public ResponseEntity<Item> updateItem(@Valid @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(item);
        if (updatedItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle 404 if item not found
        }
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an item", description = "Deletes the item based on their unique ID")
    public ResponseEntity<Item> deleteItem(@PathVariable Long id) {
        boolean isDeleted = itemService.deleteItem(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle 404 if item not found
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all items", description = "Fetches all items from the database")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get item by name", description = "Fetches item details by their unique name")
    public ResponseEntity<Item> getItemByName(@PathVariable String name) {
        Item item = itemService.getItemByName(name);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle 404 if item not found
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
