package com.example.aichat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aichat.entity.Message;
import com.example.aichat.mapper.MessageMapper;
import com.example.aichat.service.MessageService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Override
    public Message sendMessage(Long conversationId, Long userId, String content, String role) {
        Message message = new Message();
        message.setConversationId(conversationId);
        message.setUserId(userId);
        message.setContent(content);
        message.setRole(role);
        message.setSendTime(LocalDateTime.now());
        message.setIsDeleted(0);
        baseMapper.insert(message);
        return message;
    }

    @Override
    public List<Message> getMessageHistory(Long conversationId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("conversation_id", conversationId)
                    .eq("is_deleted", 0)
                    .orderByAsc("send_time");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean deleteMessage(Long messageId, Long userId) {
        // 逻辑删除消息
        Message message = new Message();
        message.setId(messageId);
        message.setIsDeleted(1);
        return baseMapper.updateById(message) > 0;
    }
}