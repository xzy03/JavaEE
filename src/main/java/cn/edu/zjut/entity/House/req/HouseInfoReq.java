package cn.edu.zjut.entity.House.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseInfoReq {
    @Schema(description = "房源ID")
    @NotBlank
    private String houseId;

    @Schema(description = "房源标题")
    @Size(min = 6, max = 30, message = "房源标题必须在6到30个字符之间")
    private String hTitle;

    @Schema(description = "房源位置")
    private String hLocation;

    @Schema(description = "租金")
    @DecimalMin(value = "0", inclusive = true, message = "租金不能小于0")
    @DecimalMax(value = "9999", inclusive = true, message = "租金不能大于9999")
    private BigDecimal hRent;

    @Schema(description = "面积（平方米）")
    @DecimalMin(value = "0", inclusive = true, message = "面积不能小于0")
    @DecimalMax(value = "2000", inclusive = true, message = "面积不能大于2000")
    private BigDecimal hArea;

    @Schema(description = "房间布局")
    private String hRooms;

    @Schema(description = "可入住时间")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hAvailableFrom;

    @Schema(description = "朝向")
    private String hDirection;

    @Schema(description = "楼层")
    @Min(value = 1, message = "楼层不能小于1")
    @Max(value = 50, message = "楼层不能大于50")
    private Integer hFloor;

    @Schema(description = "总楼层数")
    @Min(value = 1, message = "总楼层数不能小于1")
    @Max(value = 50, message = "总楼层数不能大于50")
    private Integer hTotalFloors;

    @Schema(description = "配套设施")
    private String hFacilities;

    @Schema(description = "是否允许宠物")
    private Integer hPetFriendly;

    @Schema(description = "租客要求")
    private String hTenantrequired;

    @Schema(description = "总租户数量")
    @Min(value = 1, message = "总租户数量不能小于1")
    @Max(value = 10, message = "总租户数量不能大于10")
    private Integer hTotalTenants;

    @Schema(description = "剩余空闲数量")
    @Min(value = 0, message = "剩余空闲数量不能小于0")
    @Max(value = 10, message = "剩余空闲数量不能大于10")
    private Integer hRemainingVacancies;
}
