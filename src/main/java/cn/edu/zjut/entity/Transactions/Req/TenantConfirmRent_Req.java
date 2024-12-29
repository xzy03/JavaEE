package cn.edu.zjut.entity.Transactions.Req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantConfirmRent_Req {
    private String transactionId;
    private String tenantId;
    private String tStatus;
}
