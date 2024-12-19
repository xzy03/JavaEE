package cn.edu.zjut.controller;

import cn.edu.zjut.entity.ChatMessage.ChatMessage;
import cn.edu.zjut.entity.ChatMessage.req.ChatMessageReq;
import cn.edu.zjut.service.ChatMessageService;
import cn.edu.zjut.utils.UserInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageReq chatMessageReq) {
        String fromUserId = UserInfoUtils.getCurrentUser().getUserId();
        
        ChatMessage savedMsg = chatMessageService.saveMessage(fromUserId, chatMessageReq);
        
        messagingTemplate.convertAndSendToUser(
                chatMessageReq.getToUserId(),
                "/queue/messages",
                savedMsg
        );
    }
} 