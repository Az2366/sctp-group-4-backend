package com.group4.backend.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.backend.model.Customer;
import com.group4.backend.model.Membership;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void testRegisterCustomer() throws Exception {
                Customer newCustomer = Customer.builder()
                                .customerName("Clint Barton")
                                .email("clint@avengers.com")
                                .phoneNumber("12345678")
                                .homeAddress("Somewhere in New York")
                                .dateOfBirth("1975/02/11")
                                .isActive(true)
                                .membershipType(Membership.GOLD)
                                .build();

                // Step 2: Convert the Java object to JSON using ObjectMapper
                String newCustomerAsJSON = objectMapper.writeValueAsString(newCustomer);
                System.out.println(newCustomerAsJSON);

                // Step 3: Build the request
                RequestBuilder request = MockMvcRequestBuilders.post("/customers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newCustomerAsJSON);

                mockMvc.perform(request)
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.customerName").value("Clint Barton"))
                                .andExpect(jsonPath("$.email").value("clint@avengers.com"))
                                .andExpect(jsonPath("$.phoneNumber").value("12345678"))
                                .andExpect(jsonPath("$.homeAddress").value("Somewhere in New York"))
                                .andExpect(jsonPath("$.dateOfBirth").value("1975/02/11"))
                                .andExpect(jsonPath("$.active").value(true))
                                .andExpect(jsonPath("$.membershipType").value(Membership.GOLD.toString()));

        }
}
