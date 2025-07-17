package com.paulofranklim.inditex.test.api.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "/api/v1/prices";

    @Test
    @DisplayName("Case 1: 14/06/2020 at 10:00 for product 35455 and brand 1")
    void testCase1() throws Exception {
        mockMvc.perform(get(BASE_URL)
                       .param("applicationDate", "2020-06-14T10:00:00")
                       .param("productId", "35455")
                       .param("brandId", "1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    @DisplayName("Case 2: 14/06/2020 at 16:00h for product 35455 and brand 1")
    void testCase2() throws Exception {
        mockMvc.perform(get(BASE_URL)
                       .param("applicationDate", "2020-06-14T16:00:00")
                       .param("productId", "35455")
                       .param("brandId", "1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.price", is(25.45)));
    }

    @Test
    @DisplayName("Case 3: 14/06/2020 at 21:00 for product 35455 and brand 1")
    void testCase3() throws Exception {
        mockMvc.perform(get(BASE_URL)
                       .param("applicationDate", "2020-06-14T21:00:00")
                       .param("productId", "35455")
                       .param("brandId", "1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    @DisplayName("Case 4: 15/06/2020 at 10:00 for product 35455 and brand 1")
    void testCase4() throws Exception {
        mockMvc.perform(get(BASE_URL)
                       .param("applicationDate", "2020-06-15T10:00:00")
                       .param("productId", "35455")
                       .param("brandId", "1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.price", is(30.50)));
    }

    @Test
    @DisplayName("Case 5: 16/06/2020 at 21:00h for product 35455 and brand 1")
    void testCase5() throws Exception {
        mockMvc.perform(get(BASE_URL)
                       .param("applicationDate", "2020-06-16T21:00:00")
                       .param("productId", "35455")
                       .param("brandId", "1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.price", is(38.95)));
    }

    @Test
    @DisplayName("Invalid Case: Price not found")
    void testPriceNotFound() throws Exception {
        mockMvc.perform(get(BASE_URL)
                       .param("applicationDate", "2030-01-01T00:00:00")
                       .param("productId", "99999")
                       .param("brandId", "1"))
               .andExpect(status().isNotFound());
    }
}
