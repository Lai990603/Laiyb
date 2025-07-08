package com.example.aichat.controller;

import com.example.aichat.entity.Message;
import com.example.aichat.service.MessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 发送消息
     */
    @PostMapping
    public ResponseEntity<Message> sendMessage(
            @RequestParam Long conversationId,
            @RequestParam Long userId,
            @RequestParam String content,
            @RequestParam String role) {
        Message message = messageService.sendMessage(conversationId, userId, content, role);
        return ResponseEntity.ok(message);
    }

    /**
     * 获取对话消息历史
     */
    @GetMapping("/conversation/{conversationId}")
    public ResponseEntity<List<Message>> getMessageHistory(@PathVariable Long conversationId) {
        List<Message> messages = messageService.getMessageHistory(conversationId);
        return ResponseEntity.ok(messages);
    }

    /**
     * 删除消息
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMessage(
            @PathVariable Long id,
            @RequestParam Long userId) {
        boolean success = messageService.deleteMessage(id, userId);
        return ResponseEntity.ok(success);
    }
}