package cn.edu.zjut.entity.LandlordProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LandlordRegisterReq {
    @Schema(description = "用户名",example = "admin")
    @NotBlank
    @Length(min = 6, max = 24)
    private String lAccount;

    @Schema(description = "密码",example = "123456")
    @NotBlank
    @Length(min = 6, max = 24)
    private String lPassword;

    @Schema(description = "手机号",example = "12345678911")
    @NotBlank
    @Length(min = 11, max = 11)
    private String lPhoneNumber;

    @Schema(description = "邮箱",example = "12345678988@qq.com")
    @NotBlank
    private String lEmail;

}
