package cn.edu.zjut.entity.House.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class HousePublishReq {
    @Schema(description = "小区ID")
    private String communityId;

    @Schema(description = "房屋标题")
    @NotBlank
    private String hTitle;

    @Schema(description = "房屋位置")
    @NotBlank
    private String hLocation;

    @Schema(description = "租金")
    @NotNull
    private BigDecimal hRent;

    @Schema(description = "房屋面积")
    @NotNull
    private BigDecimal hArea;

    @Schema(description = "房间布局")
    @NotBlank
    private String hRooms;

    @Schema(description = "可用日期")
    @NotNull
    private Date hAvailableFrom;

    @Schema(description = "房屋朝向")
    @NotBlank
    private String hDirection;

    @Schema(description = "楼层")
    @NotNull
    private Integer hFloor;

    @Schema(description = "总楼层数")
    @NotNull
    private Integer hTotalFloors;

    @Schema(description = "房屋设施")
    @NotBlank
    private String hFacilities;

    @Schema(description = "是否允许宠物")
    @NotNull
    private Integer hPetFriendly;

    @Schema(description = "要求租户")
    private String hTenantrequired;

    @Schema(description = "总租户数")
    @NotNull
    private Integer hTotalTenants;

    @Schema(description = "剩余空缺")
    @NotNull
    private Integer hRemainingVacancies;

    @Schema(description = "房屋证件照")
    private String lHouseLicensePhoto;

    @Schema(description = "房屋图片")
    private String lHousePhoto;
}
