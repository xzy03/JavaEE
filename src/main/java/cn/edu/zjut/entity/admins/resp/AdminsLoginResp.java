package cn.edu.zjut.entity.admins.resp;

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
public class AdminsLoginResp {
    @Schema(description = "用户名")
    private String adUsername;

    @Schema(description = "邮箱")
    private String adEmail;

    @Schema(description = "手机号")
    private String adPhone;

    @Schema(description = "token")
    private String token;
}
