package cn.edu.zjut.entity.TenantProfile.resq;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TenantLoginResp {

    @Schema(description = "用户名")
    private String tAccount;
    @Schema(description = "手机号")
    private String tPhoneNumber;
    @Schema(description = "邮箱")
    private String tEmail;
    @Schema(description = "token")
    private String token;
}
