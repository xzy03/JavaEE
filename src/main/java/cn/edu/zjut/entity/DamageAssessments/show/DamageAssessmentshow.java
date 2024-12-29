package cn.edu.zjut.entity.DamageAssessments.show;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
// 指定表名
public class DamageAssessmentshow {


    /**
     * 定损ID，来自 damage_assessments 表
     * 不能为空
     */
    @NotNull(message = "定损ID不能为空")
    private String damageId;

    /**
     * 房屋ID，来自房屋信息表
     * 不能为空
     */
    @NotNull(message = "房屋ID不能为空")
    private String houseId;

    /**
     * 租户ID，来自租户信息表
     * 不能为空
     */
    @NotNull(message = "租户ID不能为空")
    private String tenantId;

    /**
     * 附件ID，来自附件表
     * 不能为空
     */
    @NotNull(message = "附件ID不能为空")
    private String attachmentId;

    /**
     * 房东ID，来自房东信息表
     * 不能为空
     */
    @NotNull(message = "房东ID不能为空")
    private String landlordId;

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
     * 损坏描述，来自 damage_assessments 表
     * 可以为空，但若提供值则不能为空
     */
    private String daDescription;

    /**
     * 损坏评估状态，来自 damage_assessments 表
     * 默认为"未审核"，不能为空
     */
    @NotNull(message = "损坏评估状态不能为空")
    private String daStatus = "未审核"; // 默认值

    /**
     * 评估创建时间，来自 damage_assessments 表
     * 不能为空
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "评估创建时间不能为空")
    private Date daCreatedAt;

    /**
     * 附件类型，来自附件表
     * 不能为空
     */
    @NotNull(message = "附件类型不能为空")
    private String daType;

    /**
     * 附件URL地址，来自附件表
     * 不能为空
     */
    @NotNull(message = "附件URL不能为空")
    private String daUrl;




}
