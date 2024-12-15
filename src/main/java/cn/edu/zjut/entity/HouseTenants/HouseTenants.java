package cn.edu.zjut.entity.HouseTenants;

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
 * 房屋租户关系表
 * @TableName house_tenants
 */
@TableName(value ="house_tenants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseTenants implements Serializable {
    /**
     * 租户ID，唯一标识
     */
    @TableId
    private String htId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 租户租金金额
     */
    private BigDecimal htRentAmount;

    /**
     * 租户押金金额
     */
    private BigDecimal htDepositAmount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}