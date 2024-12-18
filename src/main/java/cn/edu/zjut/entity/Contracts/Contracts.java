package cn.edu.zjut.entity.Contracts;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 租房合同表
 * @TableName contracts
 */
@TableName(value ="contracts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contracts implements Serializable {
    /**
     * 合同ID，唯一标识
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String contractId;

    /**
     * 房屋ID
     */
    private String cHouseId;

    /**
     * 房东ID
     */
    private String cLandlordId;

    /**
     * 租户ID
     */
    private String cTenantId;

    /**
     * 合同开始日期
     */
    private Date cStartDate;

    /**
     * 合同结束日期
     */
    private Date cEndDate;

    /**
     * 租金金额
     */
    private BigDecimal cRentAmount;

    /**
     * 押金金额
     */
    private BigDecimal cDepositAmount;

    /**
     * 合同状态
     */
    private String cStatus;

    /**
     * 合同附加条款
     */
    private String cAdditions;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}