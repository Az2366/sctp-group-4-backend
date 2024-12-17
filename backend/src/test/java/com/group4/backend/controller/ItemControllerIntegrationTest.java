package com.group4.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.backend.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // For JSON conversion

    private Item testItem;

    @BeforeEach
    void setUp() {
        testItem = Item
                .builder()
                .itemId(1L)
                .itemName("Test Item")
                .itemDescription("This is a test item")
                .price(99.99)
                .build();
    }

    // Test for registering a new item
    @Test
    void testRegisterItem() throws Exception {
        String newItemAsJson = objectMapper.writeValueAsString(testItem);

        RequestBuilder req = MockMvcRequestBuilders.post("/items/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newItemAsJson);

        mockMvc.perform(req)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.itemId").value(1L))
                .andExpect(jsonPath("$.itemName").value("Test Item"))
                .andExpect(jsonPath("$.itemDescription").value("This is a test item"))
                .andExpect(jsonPath("$.price").value(99.99));
    }

    // Test for getting an item by ID
    @Test
    void testGetItemById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/items/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.itemId").value(1L))
                .andExpect(jsonPath("$.itemName").value("Laptop"))
                .andExpect(jsonPath("$.itemDescription").value("The best in class laptop"))
                .andExpect(jsonPath("$.price").value(1500.0));
    }

    // Test for updating an existing item
    @Test
    void testUpdateItem() throws Exception {
        Item updatedItem = Item
                .builder()
                .itemId(1L)
                .itemName("Updated Item")
                .itemDescription("Updated description")
                .price(109.99)
                .build();

        String updatedItemAsJson = objectMapper.writeValueAsString(updatedItem);

        RequestBuilder req = MockMvcRequestBuilders.put("/items/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedItemAsJson);

        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.itemId").value(1L))
                .andExpect(jsonPath("$.itemName").value("Updated Item"))
                .andExpect(jsonPath("$.itemDescription").value("Updated description"))
                .andExpect(jsonPath("$.price").value(109.99));
    }

    // Test for deleting an item
    @Test
    void testDeleteItem() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/items/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    // Test for retrieving all items
    @Test
    void testGetAllItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/items/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    // Test for getting an item by name
    @Test
    void testGetItemByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/items/name/{name}", "Laptop")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.itemName").value("Laptop"));
    }

    // Test for getting a non-existing item by ID
    @Test
    void testGetItemById_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/items/{id}", 999L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // Test for deleting a non-existing item
    @Test
    void testDeleteItem_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/items/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}
