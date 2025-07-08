package com.example.aichat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aichat.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    /**
     * 根据对话ID获取消息列表
     */
    List<Message> selectByConversationId(Long conversationId);
}