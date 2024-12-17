package cn.edu.zjut.entity.LandlordProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LandlordProfileLoginReq {
    @Schema(description = "密码")
    @NotBlank
    private String lPassword;

    @Schema(description = "手机号")
    @NotBlank
    private String lPhoneNumber;
}
