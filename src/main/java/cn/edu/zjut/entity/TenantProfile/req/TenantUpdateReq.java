package cn.edu.zjut.entity.TenantProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantUpdateReq {
    @Schema(description = "用户名",example = "admin")
    private String tAccount;
    @Schema(description = "手机号",example = "12345678911")
    private String tPhoneNumber;
    @Schema(description = "邮箱")
    private String tEmail;
    @Schema(description = "头像")
    private String tProfilePicture;
    @Schema(description = "个人介绍")
    private String tIntroduction;
}
