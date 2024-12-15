package cn.edu.zjut.entity.TenantLandlordReviews;

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
 * 房东对租客的评价表
 * @TableName tenant_landlord_reviews
 */
@TableName(value ="tenant_landlord_reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantLandlordReviews implements Serializable {
    /**
     * 租户对房东评价ID
     */
    @TableId
    private String tlReviewId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 租户评分
     */
    private Integer tlScore;

    /**
     * 租户评价
     */
    private String tlComment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}