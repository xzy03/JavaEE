package cn.edu.zjut.entity.ChatMessage.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatMessageReq {
    
    @Schema(description = "接收者ID")
    @NotBlank
    private String toUserId;
    
    @Schema(description = "消息内容")
    @NotBlank
    private String content;
    
    @Schema(description = "消息类型")
    @NotNull
    private Integer messageType;
} 