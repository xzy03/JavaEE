package cn.edu.zjut.entity.LandlordProfile.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandlordProfileLoginResp {
    @Schema(description = "用户名")
    private String lAccount;

    @Schema(description = "手机号")
    private String lPhoneNumber;

    @Schema(description = "邮箱")
    private String lEmail;

    @Schema(description = "token")
    private String token;
}
