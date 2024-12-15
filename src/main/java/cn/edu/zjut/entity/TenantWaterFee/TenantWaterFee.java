package cn.edu.zjut.entity.TenantWaterFee;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 水费详细记录表
 * @TableName tenant_water_fee
 */
@TableName(value ="tenant_water_fee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantWaterFee implements Serializable {
    /**
     * 租户水费ID，唯一标识
     */
    @TableId
    private String tenantWaterFeeId;

    /**
     * 水费ID
     */
    private String waterFeeId;

    /**
     * 交易ID
     */
    private String transactionId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 水费金额
     */
    private BigDecimal twFee;

    /**
     * 水费比例
     */
    private Double twRatio;

    /**
     * 水费状态
     */
    private String twStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}