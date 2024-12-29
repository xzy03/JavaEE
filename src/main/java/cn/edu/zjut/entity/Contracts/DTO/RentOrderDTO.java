package cn.edu.zjut.entity.Contracts.DTO;

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
public class RentOrderDTO {
    // 租户ID
    private String tenantId;

    // 房东ID
    private String landlordId;

    // 合同ID
    private String contractId;

    // 房租金额
    private BigDecimal rentAmount;

    // 交易类型（如：房租）
    private String transactionType;

    // 交易状态（如：待支付）
    private String transactionStatus;

    // 交易名称（如：“2024年1月房租”）
    private String transactionName;

    // 交易费用
    private BigDecimal transactionFees;

    // 支付时间
    private Date transactionTime;
}
