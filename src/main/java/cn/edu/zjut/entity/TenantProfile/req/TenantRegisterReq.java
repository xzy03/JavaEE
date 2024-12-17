package cn.edu.zjut.entity.TenantProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TenantRegisterReq {
    @Schema(description = "用户名",example = "admin")
    @NotBlank
    private String tAccount;
    @Schema(description = "密码",example = "123456")
    @NotBlank
    private String tPassword;
    @Schema(description = "电话",example = "12345678911")
    @NotBlank
    private String tPhoneNumber;
    @Schema(description = "邮箱",example = "123456@qq.com")
    @NotBlank
    private String tEmail;
}
