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
public class LandlordRefundDepositDTO {
    private String landlordId;
    private String tenantId;
    private String tenantName;
    private String transactionId;
    private BigDecimal depositAmount;
    private String paymentStatus;
}
