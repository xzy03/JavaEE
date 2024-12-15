package cn.edu.zjut.entity.PromotionCampaigns;

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
 * 推广引流产品表
 * @TableName promotion_campaigns
 */
@TableName(value ="promotion_campaigns")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionCampaigns implements Serializable {
    /**
     * 促销活动ID，唯一标识
     */
    @TableId
    private String promotionId;

    /**
     * 促销活动名称
     */
    private String pcName;

    /**
     * 促销活动描述
     */
    private String pcDescription;

    /**
     * 促销活动状态
     */
    private BigDecimal pcStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}