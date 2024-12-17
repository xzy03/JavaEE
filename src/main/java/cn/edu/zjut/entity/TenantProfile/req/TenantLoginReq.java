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

public class TenantLoginReq {
    @Schema(description = "手机号")
    @NotBlank
    private String phoneNum;
    @Schema(description = "密码")
    @NotBlank
    private String password;
}
