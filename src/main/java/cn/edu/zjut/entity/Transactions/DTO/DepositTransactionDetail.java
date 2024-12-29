package cn.edu.zjut.entity.Transactions.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositTransactionDetail {
    private String transactionId; // 交易 ID
    private BigDecimal depositAmount; // 押金金额
    private String htitle; // 房源名称
    private String tenantId; // 租户 ID
    private String landlordId; // 房东 ID
    private String transactionType; // 交易类型
    private String transactionStatus; // 交易状态
}
