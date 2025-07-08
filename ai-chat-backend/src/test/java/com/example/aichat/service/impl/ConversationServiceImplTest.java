package com.example.aichat.service.impl;

import com.example.aichat.entity.Conversation;
import com.example.aichat.mapper.ConversationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ConversationServiceImplTest {

    @Mock
    private ConversationMapper conversationMapper;

    @InjectMocks
    private ConversationServiceImpl conversationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateConversation() {
        Long userId = 1L;
        String title = "测试对话";
        Conversation conversation = new Conversation();
        conversation.setUserId(userId);
        conversation.setTitle(title);

        when(conversationMapper.insert(any(Conversation.class))).thenReturn(1);

        Conversation result = conversationService.createConversation(userId, title);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(title, result.getTitle());
    }
} 