package cn.edu.zjut.entity.LandlordReviews;

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
 * @TableName landlord_reviews
 */
@TableName(value ="landlord_reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LandlordReviews implements Serializable {
    /**
     * 评价ID，唯一标识
     */
    @TableId
    private String llReviewId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 房东评价
     */
    private String llComment;

    /**
     * 房东评分
     */
    private Integer llScore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}