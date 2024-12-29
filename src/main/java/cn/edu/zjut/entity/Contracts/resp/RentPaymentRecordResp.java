package cn.edu.zjut.entity.Contracts.resp;

import cn.edu.zjut.entity.Contracts.DTO.RentPaymentRecordDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentPaymentRecordResp {
    private List<RentPaymentRecordDTO> rentPaymentRecordDTOList;
}
