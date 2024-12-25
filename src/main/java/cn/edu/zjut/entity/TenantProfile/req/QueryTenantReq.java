package cn.edu.zjut.entity.TenantProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryTenantReq {
    @Schema(description = "租户所在大学")
    private String tUniversity;

    @Schema(description = "租户专业")
    private String tMajor;

    @Schema(description = "租户身份状态")
    private String tIdentityStatus;

    @Schema(description = "租户状态")
    private String tStatus;

    @Schema(description = "性别")
    private String tSex;

    @Schema(description = "出生年月起始时间")
    private Date tBirthStart;

    @Schema(description = "出生年月结束时间")
    private Date tBirthEnd;
}
