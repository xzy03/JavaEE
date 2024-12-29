package cn.edu.zjut.entity.Transactions.Req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantPayDepositReq {
    private String tenantId;
    private String transactionId;


}
