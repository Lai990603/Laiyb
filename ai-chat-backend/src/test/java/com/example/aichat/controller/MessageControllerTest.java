package com.example.aichat.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSendMessage() throws Exception {
        mockMvc.perform(post("/messages")
                .param("conversationId", "1")
                .param("userId", "2")
                .param("content", "你好")
                .param("role", "user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("你好"));
    }

    @Test
    void testGetMessageHistory() throws Exception {
        mockMvc.perform(get("/messages/conversation/1"))
                .andExpect(status().isOk());
    }
} 