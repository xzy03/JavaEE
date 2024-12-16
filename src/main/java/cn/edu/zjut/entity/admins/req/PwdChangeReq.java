package cn.edu.zjut.entity.admins.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * 管理员修改密码信息请求
 * @Author 项郑毅
 * @Date 2024/12/16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PwdChangeReq {
    @Schema(description = "手机号")
    @Length(min = 11, max = 11)
    @NotBlank
    private String phoneNum;
    @Schema(description = "新修改密码")
    @Length(min = 6, max = 24)
    @NotBlank
    private String newPassword;
    @Schema(description = "确认密码")
    @Length(min = 6, max = 24)
    @NotBlank
    private String confirmPassword;
}
