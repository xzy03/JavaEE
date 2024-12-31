package cn.edu.zjut.entity.LandlordProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 房东支付请求
 * @Author bc
 * @Date 2024/12/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LandlordPaymentReq {
    @Schema(description = "修改金额")
    @NotNull(message = "修改金额不能为空")
    private Double amount;
}
