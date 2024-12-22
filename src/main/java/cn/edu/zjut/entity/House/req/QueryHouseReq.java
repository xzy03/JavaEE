package cn.edu.zjut.entity.House.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryHouseReq {
    @Schema(description = "小区ID")
    private String communityId;

    @Schema(description = "房东ID")
    private String landlordId;

    @Schema(description = "房源位置")
    private String hLocation;

    @Schema(description = "最小租金")
    private BigDecimal minRent;

    @Schema(description = "最大租金")
    private BigDecimal maxRent;

    @Schema(description = "最小面积")
    private BigDecimal minArea;

    @Schema(description = "最大面积")
    private BigDecimal maxArea;

    @Schema(description = "朝向")
    private String hDirection;

    @Schema(description = "楼层")
    private Integer hFloor;

    @Schema(description = "总楼层数")
    private Integer hTotalFloors;

    @Schema(description = "是否允许宠物")
    private Integer hPetFriendly;

    @Schema(description = "总租户数量")
    private Integer hTotalTenants;

    @Schema(description = "剩余空闲数量")
    private Integer hRemainingVacancies;
}
