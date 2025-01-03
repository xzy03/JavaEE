package cn.edu.zjut.entity.Contracts.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractsIdReq {
    @Schema(description = "合同ID")
    @NotBlank(message = "合同ID不能为空")
    private String contractsId;
}
