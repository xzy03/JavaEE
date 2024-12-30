package cn.edu.zjut.entity.Transactions.resp;

import cn.edu.zjut.entity.Transactions.Transactions;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositListInfo {
    @Schema(description = "房租支付记录列表")
    private List<Transactions> depositList;
}
