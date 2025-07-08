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
class ConversationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateConversation() throws Exception {
        mockMvc.perform(post("/conversations")
                .param("userId", "1")
                .param("title", "测试对话"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("测试对话"));
    }

    @Test
    void testGetUserConversations() throws Exception {
        mockMvc.perform(get("/conversations/user/1"))
                .andExpect(status().isOk());
    }
} 