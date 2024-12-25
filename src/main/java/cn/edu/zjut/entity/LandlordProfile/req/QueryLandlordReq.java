package cn.edu.zjut.entity.LandlordProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryLandlordReq {
    @Schema(description = "房东身份验证状态")
    private String lHouseStatus;

    @Schema(description = "房东身份证审核状态")
    private String lStatus;
}
