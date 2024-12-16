package com.group4.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group4.backend.exception.ItemNotFoundException;
import com.group4.backend.model.Item;
import com.group4.backend.repository.ItemRepository;

class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample item setup
        item = new Item();
        item.setItemId(1L);
        item.setItemName("Test Item");
        item.setItemDescription("Test Description");
        item.setPrice(100.0);
    }

    @Test
    void testCreateItem() {
        when(itemRepository.save(item)).thenReturn(item);

        Item savedItem = itemService.createItem(item);

        assertNotNull(savedItem);
        assertEquals("Test Item", savedItem.getItemName());
        assertEquals("Test Description", savedItem.getItemDescription());
        assertEquals(100.0, savedItem.getPrice());
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    void testGetItemById_Success() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Item foundItem = itemService.getItemById(1L);

        assertNotNull(foundItem);
        assertEquals("Test Item", foundItem.getItemName());
        assertEquals("Test Description", foundItem.getItemDescription());
        assertEquals(100.0, foundItem.getPrice());
        verify(itemRepository, times(1)).findById(1L);
    }

    @Test
    void testGetItemById_NotFound() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemService.getItemById(1L));
        verify(itemRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllItems() {
        when(itemRepository.findAll()).thenReturn(Arrays.asList(item, item));

        var items = itemService.getAllItems();

        assertNotNull(items);
        assertEquals(2, items.size());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void testGetAllItems_NoItems() {
        when(itemRepository.findAll()).thenReturn(Collections.emptyList());

        var items = itemService.getAllItems();

        assertNotNull(items);
        assertTrue(items.isEmpty());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void testUpdateItem_Success() {
        Item updatedItem = new Item();
        updatedItem.setItemId(1L);
        updatedItem.setItemName("Updated Item");
        updatedItem.setItemDescription("Updated Description");
        updatedItem.setPrice(200.0);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenReturn(updatedItem);

        Item result = itemService.updateItem(updatedItem);

        assertNotNull(result);
        assertEquals("Updated Item", result.getItemName());
        assertEquals("Updated Description", result.getItemDescription());
        assertEquals(200.0, result.getPrice());
        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void testUpdateItem_NotFound() {
        Item updatedItem = new Item();
        updatedItem.setItemId(1L);

        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemService.updateItem(updatedItem));
        verify(itemRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteItem_Success() {
        when(itemRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = itemService.deleteItem(1L);

        assertTrue(isDeleted);
        verify(itemRepository, times(1)).existsById(1L);
        verify(itemRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteItem_NotFound() {
        when(itemRepository.existsById(1L)).thenReturn(false);

        boolean isDeleted = itemService.deleteItem(1L);

        assertFalse(isDeleted);
        verify(itemRepository, times(1)).existsById(1L);
        verify(itemRepository, never()).deleteById(1L);
    }

    @Test
    void testGetItemByName_Success() {
        when(itemRepository.findByItemName("Test Item")).thenReturn(item);

        Item foundItem = itemService.getItemByName("Test Item");

        assertNotNull(foundItem);
        assertEquals("Test Item", foundItem.getItemName());
        verify(itemRepository, times(1)).findByItemName("Test Item");
    }

    @Test
    void testGetItemByName_NotFound() {
        when(itemRepository.findByItemName("Nonexistent Item")).thenReturn(null);

        Item foundItem = itemService.getItemByName("Nonexistent Item");

        assertNull(foundItem);
        verify(itemRepository, times(1)).findByItemName("Nonexistent Item");
    }
}
