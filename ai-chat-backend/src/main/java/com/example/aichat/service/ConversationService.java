package com.example.aichat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.aichat.entity.Conversation;
import java.util.List;

public interface ConversationService extends IService<Conversation> {
    /**
     * 创建新对话
     */
    Conversation createConversation(Long userId, String title);

    /**
     * 获取用户对话列表
     */
    List<Conversation> getUserConversationList(Long userId);

    /**
     * 更新对话标题
     */
    boolean updateConversationTitle(Long id, String title);

    /**
     * 删除对话
     */
    boolean deleteConversation(Long id, Long userId);
}