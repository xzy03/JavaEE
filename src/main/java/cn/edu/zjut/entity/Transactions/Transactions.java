package cn.edu.zjut.entity.Transactions;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 交易记录表
 * @TableName transactions
 */
@TableName(value ="transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transactions implements Serializable {
    /**
     * 交易ID，唯一标识
     */
    @TableId
    private String transactionId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 交易类型
     */
    private String tTransactionType;

    /**
     * 交易金额
     */
    private BigDecimal tAmount;

    /**
     * 交易状态
     */
    private String tStatus;

    /**
     * 交易名称
     */
    private String tName;

    /**
     * 是否收费
     */
    private Integer tCharge;

    /**
     * 交易费用
     */
    private BigDecimal tFees;

    /**
     * 交易来源
     */
    private String tTransactionSource;

    /**
     * 支付时间
     */
    private Date tPaytime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}