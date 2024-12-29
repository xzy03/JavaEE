package cn.edu.zjut.entity.Transactions.DTO;

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
public class TenantPaymentDTO {
    private String houseName;       // 房屋名称
    private BigDecimal paymentAmount; // 支付金额
    private String transactionStatus; // 交易状态，如"已支付"、"待支付"
    private Date transactionTime;    // 交易时间
}
