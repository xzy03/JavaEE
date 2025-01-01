package cn.edu.zjut.entity.Transactions.resp;

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
public class QueryTransactionResp {
    @Schema(description = "交易ID")
    private String transactionId;

    @Schema(description = "房东姓名")
    private String lName;

    @Schema(description = "租户姓名")
    private String tName;

    @Schema(description = "交易类型")
    private String tTransactionType;

    @Schema(description = "交易金额")
    private BigDecimal tAmount;

    @Schema(description = "交易状态")
    private String tStatus;

    @Schema(description = "支付时间")
    private Date tPaytime;

}
