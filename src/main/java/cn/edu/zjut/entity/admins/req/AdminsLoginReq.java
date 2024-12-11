package cn.edu.zjut.entity.admins.req;

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
public class AdminsLoginReq {
    @Schema(description = "用户名")
    @NotBlank
    private String adUsername;

    @Schema(description = "密码")
    @NotBlank
    @Length(min = 6, max = 24)
    private String adPasswordHash;
}
