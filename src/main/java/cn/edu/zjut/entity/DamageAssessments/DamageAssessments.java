package cn.edu.zjut.entity.DamageAssessments;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 定损表
 * @TableName damage_assessments
 */
@TableName(value ="damage_assessments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DamageAssessments implements Serializable {
    /**
     * 损坏评估ID，唯一标识
     */
    @TableId
    private String damageId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 附件ID
     */
    private String attachmentId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 损坏评估名称
     */
    private String daName;

    /**
     * 损坏金额
     */
    private BigDecimal daAmount;

    /**
     * 损坏描述
     */
    private String daDescription;

    /**
     * 损坏评估状态
     */
    private String daStatus;

    /**
     * 评估创建时间
     */
    private Date daCreatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}