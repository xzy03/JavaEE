package cn.edu.zjut.entity.TenantElectricityFee;

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
 * 电费详细记录表
 * @TableName tenant_electricity_fee
 */
@TableName(value ="tenant_electricity_fee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantElectricityFee implements Serializable {
    /**
     * 租户电费ID，唯一标识
     */
    @TableId
    private String tenantElectricityFeeId;

    /**
     * 电费ID
     */
    private String electricityFeeId;

    /**
     * 交易ID
     */
    private String transactionId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 电费金额
     */
    private BigDecimal teFee;

    /**
     * 电费比例
     */
    private Double teRatio;

    /**
     * 电费状态
     */
    private String teStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}