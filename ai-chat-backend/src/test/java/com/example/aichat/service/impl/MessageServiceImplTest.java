package com.example.aichat.service.impl;

import com.example.aichat.entity.Message;
import com.example.aichat.mapper.MessageMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MessageServiceImplTest {

    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private MessageServiceImpl messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        Long conversationId = 1L;
        Long userId = 2L;
        String content = "你好";
        String role = "user";
        Message message = new Message();
        message.setConversationId(conversationId);
        message.setUserId(userId);
        message.setContent(content);
        message.setRole(role);

        when(messageMapper.insert(any(Message.class))).thenReturn(1);

        Message result = messageService.sendMessage(conversationId, userId, content, role);

        assertNotNull(result);
        assertEquals(content, result.getContent());
        assertEquals(role, result.getRole());
    }
} 