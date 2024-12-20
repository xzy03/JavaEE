package cn.edu.zjut.entity.LandlordProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandlordProfileUpdateReq {
    @Schema(description = "用户名",example = "admin")
    private String lAccount;
    @Schema(description = "手机号",example = "12345678911")
    private String lPhoneNumber;
    @Schema(description = "邮箱")
    private String lEmail;
    @Schema(description = "头像")
    private String lProfilePicture;
} 