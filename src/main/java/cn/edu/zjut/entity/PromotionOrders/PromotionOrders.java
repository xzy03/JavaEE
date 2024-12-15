package cn.edu.zjut.entity.PromotionOrders;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推广订单表
 * @TableName promotion_orders
 */
@TableName(value ="promotion_orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionOrders implements Serializable {
    /**
     * 促销订单ID，唯一标识
     */
    @TableId
    private String poOrderId;

    /**
     * 促销活动ID
     */
    private String promotionId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 促销订单日期
     */
    private Date poDate;

    /**
     * 促销订单状态
     */
    private String poWtStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}