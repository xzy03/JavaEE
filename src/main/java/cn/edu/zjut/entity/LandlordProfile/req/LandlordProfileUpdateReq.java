package cn.edu.zjut.entity.LandlordProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandlordProfileUpdateReq {
    @Schema(description = "用户名",example = "admin")
    @Length(min = 6, max = 24, message = "用户名长度为6-24")
    private String lAccount;

    @Schema(description = "手机号",example = "12345678911")
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号必须是11位数字")
    private String lPhoneNumber;

    @Schema(description = "邮箱")
    private String lEmail;

    @Schema(description = "头像")
    private String lProfilePicture;
} 