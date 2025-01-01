package cn.edu.zjut.entity.LandlordProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandlordProfileRegisterReq {
    @Schema(description = "用户名",example = "admin")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 6, max = 24, message = "用户名长度为6-24")
    private String lAccount;

    @Schema(description = "密码",example = "123456")
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 24, message = "密码长度为6-24")
    private String lPassword;

    @Schema(description = "手机号",example = "12345678911")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号必须是11位数字")
    private String lPhoneNumber;

    @Schema(description = "邮箱",example = "12345678988@qq.com")
    @NotBlank(message = "邮箱不能为空")
    private String lEmail;

}
