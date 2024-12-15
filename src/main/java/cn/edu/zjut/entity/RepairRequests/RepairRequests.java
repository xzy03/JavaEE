package cn.edu.zjut.entity.RepairRequests;

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
 * 报修管理表
 * @TableName repair_requests
 */
@TableName(value ="repair_requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepairRequests implements Serializable {
    /**
     * 维修请求ID，唯一标识
     */
    @TableId
    private String repairId;

    /**
     * 附件ID
     */
    private String attachmentId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 维修项目
     */
    private String reItems;

    /**
     * 维修描述
     */
    private String reDescription;

    /**
     * 维修状态
     */
    private String reStatus;

    /**
     * 维修结果
     */
    private String reResult;

    /**
     * 维修结果描述
     */
    private String reResultDescription;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}