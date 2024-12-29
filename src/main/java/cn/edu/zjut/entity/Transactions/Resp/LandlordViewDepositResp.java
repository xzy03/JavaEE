package cn.edu.zjut.entity.Transactions.Resp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LandlordViewDepositResp {
    private String tenantId;
    private String tenantName;
    private BigDecimal depositAmount;
    private String paymentStatus;
}
