package cn.edu.zjut.entity.Transactions.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionIdReq {
    @Schema(description = "交易ID")
    private String transactionId;
}
