package cn.edu.zjut.entity.TenantPropertyReviews;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 租客对房源的评价表
 * @TableName tenant_property_reviews
 */
@TableName(value ="tenant_property_reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantPropertyReviews implements Serializable {
    /**
     * 租户房产评价ID
     */
    @TableId
    private String tpReviewId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 租户评分
     */
    private Integer tpScore;

    /**
     * 租户评价
     */
    private String tpComment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}