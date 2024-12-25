package cn.edu.zjut.entity.Contracts.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractsDetailResp {
    @Schema(description = "房东姓名")
    private String lName;

    @Schema(description = "租客姓名")
    private String tName;

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
