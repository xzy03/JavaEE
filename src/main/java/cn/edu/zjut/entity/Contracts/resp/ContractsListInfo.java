package cn.edu.zjut.entity.Contracts.resp;

import cn.edu.zjut.entity.Contracts.Contracts;
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
public class ContractsListInfo {
    @Schema(description = "合同列表")
    List<Contracts> contractsList;
}
