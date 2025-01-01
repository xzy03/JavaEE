package cn.edu.zjut.entity.House.req;

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
public class HouseIdReq {
    @Schema(description = "房源ID")
    @NotBlank(message = "房源ID不能为空")
    private String houseId;
}
