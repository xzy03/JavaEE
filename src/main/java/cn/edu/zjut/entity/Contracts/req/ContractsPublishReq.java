package cn.edu.zjut.entity.Contracts.req;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.math.BigDecimal;
/*
    * 发布合同请求参数
    * @Author 项郑毅
    * @Date 2024/12/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractsPublishReq {

    @Schema(description = "房屋ID")
    private String cHouseId;

    @Schema(description = "租客ID")
    private String cTenantId;

    @Schema(description = "合同开始日期")
    private Date cStartDate;

    @Schema(description = "合同结束日期")
    private Date cEndDate;

    @Schema(description = "租金金额")
    private BigDecimal cRentAmount;

    @Schema(description = "押金金额")
    private BigDecimal cDepositAmount;

    @Schema(description = "附加条款")
    private String cAdditions;
}