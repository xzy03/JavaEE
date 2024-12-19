package cn.edu.zjut.service;

import cn.edu.zjut.entity.ChatMessage.ChatMessage;
import cn.edu.zjut.entity.ChatMessage.req.ChatMessageReq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86173
* @description 针对表【chat_message(聊天消息表)】的数据库操作Service
* @createDate 2024-12-18 21:30:42
*/
public interface ChatMessageService extends IService<ChatMessage> {
    ChatMessage saveMessage(String fromUserId, ChatMessageReq req);

    // 获取历史消息
    List<ChatMessage> getHistoryMessages(String fromUserId, String toUserId);

    // 获取未读消息数
    Integer getUnreadCount(String userId);

    // 标记消息为已读
    void markAsRead(String fromUserId, String toUserId);
}
