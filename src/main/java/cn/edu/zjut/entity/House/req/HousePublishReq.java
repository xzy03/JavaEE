package cn.edu.zjut.entity.House.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "房屋标题不能为空")
    private String hTitle;

    @Schema(description = "房屋位置")
    @NotBlank(message = "房屋位置不能为空")
    private String hLocation;

    @Schema(description = "租金")
    @NotNull(message = "租金不能为空")
    @DecimalMin(value = "0", inclusive = true, message = "租金不能小于0")
    @DecimalMax(value = "9999", inclusive = true, message = "租金不能大于9999")
    private BigDecimal hRent;

    @Schema(description = "房屋面积")
    @NotNull(message = "面积不能为空")
    @DecimalMin(value = "0", inclusive = true, message = "面积不能小于0")
    @DecimalMax(value = "2000", inclusive = true, message = "面积不能大于2000")
    private BigDecimal hArea;

    @Schema(description = "房间布局")
    @NotBlank(message = "房间布局不能为空")
    private String hRooms;

    @Schema(description = "可用日期")
    @NotNull(message = "可用日期不能为空")
    private Date hAvailableFrom;

    @Schema(description = "房屋朝向")
    @NotBlank(message = "房屋朝向不能为空")
    private String hDirection;

    @Schema(description = "楼层")
    @NotNull(message = "楼层不能为空")
    @Min(value = 1, message = "楼层不能小于1")
    @Max(value = 50, message = "楼层不能大于50")
    private Integer hFloor;

    @Schema(description = "总楼层数")
    @NotNull(message = "总楼层数不能为空")
    @Min(value = 1, message = "楼层不能小于1")
    @Max(value = 50, message = "楼层不能大于50")
    private Integer hTotalFloors;

    @Schema(description = "房屋设施")
    @NotBlank(message = "房屋设施不能为空")
    private String hFacilities;

    @Schema(description = "是否允许宠物")
    @NotNull(message = "是否允许宠物不能为空")
    private Integer hPetFriendly;

    @Schema(description = "要求租户")
    private String hTenantrequired;

    @Schema(description = "总租户数")
    @NotNull(message = "总租户数不能为空")
    @Min(value = 1, message = "总租户数量不能小于1")
    @Max(value = 10, message = "总租户数量不能大于10")
    private Integer hTotalTenants;

    @Schema(description = "剩余空缺")
    @NotNull(message = "剩余空缺不能为空")
    @Min(value = 0, message = "剩余空闲数量不能小于0")
    @Max(value = 10, message = "剩余空闲数量不能大于10")
    private Integer hRemainingVacancies;
}
