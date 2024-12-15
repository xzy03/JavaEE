package cn.edu.zjut.entity.TenantRoommateReviews;

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
 * 租客对合租人的评价表
 * @TableName tenant_roommate_reviews
 */
@TableName(value ="tenant_roommate_reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantRoommateReviews implements Serializable {
    /**
     * 租户室友评价ID
     */
    @TableId
    private String trReviewId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 室友ID
     */
    private String tenTenantId;

    /**
     * 室友评分
     */
    private Integer trScore;

    /**
     * 室友评价
     */
    private String trComment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}