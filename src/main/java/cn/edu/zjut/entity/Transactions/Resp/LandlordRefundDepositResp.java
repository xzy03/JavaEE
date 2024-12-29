package cn.edu.zjut.entity.Transactions.Resp;

import cn.edu.zjut.entity.Transactions.DTO.LandlordRefundDepositDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LandlordRefundDepositResp {
    private List<LandlordRefundDepositDTO> landlordRefundDepositDTOList;
}
