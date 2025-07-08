package com.example.aichat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.aichat.entity.Message;
import java.util.List;

public interface MessageService extends IService<Message> {
    /**
     * 发送消息
     */
    Message sendMessage(Long conversationId, Long userId, String content, String role);

    /**
     * 获取对话消息历史
     */
    List<Message> getMessageHistory(Long conversationId);

    /**
     * 删除对话中的消息
     */
    boolean deleteMessage(Long messageId, Long userId);
}