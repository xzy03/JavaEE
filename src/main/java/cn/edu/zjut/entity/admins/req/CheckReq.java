package cn.edu.zjut.entity.admins.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckReq {
    @Schema(description = "用户id")
    String Id;
    @Schema(description = "审核状态")
    String content;
}
