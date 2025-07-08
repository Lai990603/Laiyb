package com.example.aichat.controller;

import com.example.aichat.entity.Conversation;
import com.example.aichat.service.ConversationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    /**
     * 创建新对话
     */
    @PostMapping
    public ResponseEntity<Conversation> createConversation(
            @RequestParam Long userId,
            @RequestParam(required = false) String title) {
        Conversation conversation = conversationService.createConversation(userId, title);
        return ResponseEntity.ok(conversation);
    }

    /**
     * 获取用户对话列表
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Conversation>> getUserConversations(@PathVariable Long userId) {
        List<Conversation> conversations = conversationService.getUserConversationList(userId);
        return ResponseEntity.ok(conversations);
    }

    /**
     * 更新对话标题
     */
    @PutMapping("/{id}/title")
    public ResponseEntity<Boolean> updateConversationTitle(
            @PathVariable Long id,
            @RequestParam String title) {
        boolean success = conversationService.updateConversationTitle(id, title);
        return ResponseEntity.ok(success);
    }

    /**
     * 删除对话
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteConversation(
            @PathVariable Long id,
            @RequestParam Long userId) {
        boolean success = conversationService.deleteConversation(id, userId);
        return ResponseEntity.ok(success);
    }
}