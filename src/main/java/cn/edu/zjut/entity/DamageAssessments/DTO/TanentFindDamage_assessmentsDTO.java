package cn.edu.zjut.entity.DamageAssessments.DTO;

import cn.edu.zjut.entity.DamageAssessments.DTO.FindDamage_assessmentsDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TanentFindDamage_assessmentsDTO {

    /**
     * 定损ID，来自 damage_assessments 表
     * 不能为空
     */
    @NotNull(message = "定损ID不能为空")
    private String damageId;

    @NotNull(message = "房东名称不能为空")
    private String lName ;


    /**
     * 房屋ID，来自房屋信息表
     * 不能为空
     */
    @NotNull(message = "房屋ID不能为空")
    private String houseId;

    @NotNull(message = "房屋标题不能为空")
    private String hTitle;

    @NotNull(message = "租客id不能为空")
    private String tenantId;


    private String tName;
    /**
     * 损坏评估名称，来自 damage_assessments 表
     * 不能为空
     */
    @NotEmpty(message = "损坏评估名称不能为空")
    private String daName;

    /**
     * 损坏金额，来自 damage_assessments 表
     * 必须大于零
     */
    @Positive(message = "损坏金额必须大于零")
    private BigDecimal daAmount;


    /**
     * 损坏评估状态，来自 damage_assessments 表
     * 默认为"未审核"，不能为空
     */
    @NotNull(message = "损坏评估状态不能为空")
    private String daStatus = "未审核"; // 默认值
}
