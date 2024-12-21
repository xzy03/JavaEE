package cn.edu.zjut.entity.House.resp;

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
public class HouseDetail {
    @Schema(description = "房源标题")
    private String hTitle;

    @Schema(description = "房源位置")
    private String hLocation;

    @Schema(description = "租金")
    private BigDecimal hRent;

    @Schema(description = "面积（平方米）")
    private BigDecimal hArea;

    @Schema(description = "房间布局")
    private String hRooms;

    @Schema(description = "可入住时间")
    private Date hAvailableFrom;

    @Schema(description = "朝向")
    private String hDirection;

    @Schema(description = "楼层")
    private Integer hFloor;

    @Schema(description = "总楼层数")
    private Integer hTotalFloors;

    @Schema(description = "配套设施")
    private String hFacilities;

    @Schema(description = "是否允许宠物")
    private Integer hPetFriendly;

    @Schema(description = "租客要求")
    private String hTenantrequired;

    @Schema(description = "总租户数量")
    private Integer hTotalTenants;

    @Schema(description = "剩余空闲数量")
    private Integer hRemainingVacancies;

    @Schema(description = "房产证图片")
    private String lHouseLicensePhoto;

    @Schema(description = "房屋图片")
    private String lHousePhoto;

    @Schema(description = "房东手机号")
    private String lPhoneNumber;

    @Schema(description = "房东邮箱")
    private String lEmail;

    @Schema(description = "房东姓名")
    private String lName;
}
