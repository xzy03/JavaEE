package cn.edu.zjut.entity.admins.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
    * 管理员信息请求
    * @author 项郑毅
    * @date 2024-12-15
 */
public class AdminsInfoReq {
    @Schema(description = "用户名", nullable = true)
    @Nullable
    private String adUsername;

    @Schema(description = "邮箱", nullable = true)
    @Nullable
    private String adEmail;

    @Schema(description = "手机号", nullable = true)
    @Nullable
    private String adPhone;
}
