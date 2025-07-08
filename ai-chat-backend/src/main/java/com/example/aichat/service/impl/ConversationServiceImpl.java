package com.example.aichat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aichat.entity.Conversation;
import com.example.aichat.mapper.ConversationMapper;
import com.example.aichat.service.ConversationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation> implements ConversationService {

    @Override
    public Conversation createConversation(Long userId, String title) {
        Conversation conversation = new Conversation();
        conversation.setUserId(userId);
        // 如果标题为空，设置默认标题
        conversation.setTitle(title == null || title.trim().isEmpty() ? "新对话" : title);
        conversation.setCreateTime(LocalDateTime.now());
        conversation.setUpdateTime(LocalDateTime.now());
        conversation.setIsDeleted(0);
        baseMapper.insert(conversation);
        return conversation;
    }

    @Override
    public List<Conversation> getUserConversationList(Long userId) {
        QueryWrapper<Conversation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                    .eq("is_deleted", 0)
                    .orderByDesc("update_time");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean updateConversationTitle(Long id, String title) {
        Conversation conversation = new Conversation();
        conversation.setId(id);
        conversation.setTitle(title);
        conversation.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(conversation) > 0;
    }

    @Override
    public boolean deleteConversation(Long id, Long userId) {
        // 逻辑删除
        Conversation conversation = new Conversation();
        conversation.setId(id);
        conversation.setIsDeleted(1);
        conversation.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(conversation) > 0;
    }
}