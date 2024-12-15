package cn.edu.zjut.entity.ElectricityFees;

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
 * 电费表
 * @TableName electricity_fees
 */
@TableName(value ="electricity_fees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElectricityFees implements Serializable {
    /**
     * 电费ID，唯一标识
     */
    @TableId
    private String electricityFeeId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 电费金额
     */
    private BigDecimal elFee;

    /**
     * 电费月份
     */
    private Date elMonth;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}