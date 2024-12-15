package cn.edu.zjut.entity.WaterFees;

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
 * 水费表
 * @TableName water_fees
 */
@TableName(value ="water_fees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaterFees implements Serializable {
    /**
     * 水费ID，唯一标识
     */
    @TableId
    private String waterFeeId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 水费金额
     */
    private BigDecimal wtFee;

    /**
     * 水费月份
     */
    private Date wtMonth;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}