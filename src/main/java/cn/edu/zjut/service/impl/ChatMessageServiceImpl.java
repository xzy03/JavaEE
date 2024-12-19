package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.ChatMessage.req.ChatMessageReq;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.ChatMessage.ChatMessage;
import cn.edu.zjut.service.ChatMessageService;
import cn.edu.zjut.mapper.ChatMessageMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author 86173
* @description 针对表【chat_message(聊天消息表)】的数据库操作Service实现
* @createDate 2024-12-18 21:30:42
*/
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage>
    implements ChatMessageService{
    @Override
    public ChatMessage saveMessage(String fromUserId, ChatMessageReq req){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setFromUserId(fromUserId);
        chatMessage.setToUserId(req.getToUserId());
        chatMessage.setContent(req.getContent());
        chatMessage.setSendTime(new Date());
        this.save(chatMessage);
        return chatMessage;
    }

    @Override
    public List<ChatMessage> getHistoryMessages(String fromUserId, String toUserId){
        return this.lambdaQuery().eq(ChatMessage::getFromUserId, fromUserId)
            .eq(ChatMessage::getToUserId, toUserId)
            .or()
            .eq(ChatMessage::getFromUserId, toUserId)
            .eq(ChatMessage::getToUserId, fromUserId)
            .orderByDesc(ChatMessage::getSendTime)
            .list();
    }

    @Override
    public Integer getUnreadCount(String userId){
        long count = this.lambdaQuery().eq(ChatMessage::getToUserId, userId)
                .eq(ChatMessage::getIsRead, false)
                .count();
        return (int) count;
    }

    @Override
    public void markAsRead(String fromUserId, String toUserId){
        this.lambdaUpdate().eq(ChatMessage::getFromUserId, fromUserId)
            .eq(ChatMessage::getToUserId, toUserId)
            .set(ChatMessage::getIsRead, true)
            .update();
    }
}




