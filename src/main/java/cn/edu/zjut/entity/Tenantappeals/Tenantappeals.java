package cn.edu.zjut.entity.Tenantappeals;

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
 * 租客申诉表
 * @TableName tenantappeals
 */
@TableName(value ="tenantappeals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tenantappeals implements Serializable {
    /**
     * 租户申诉ID，唯一标识
     */
    @TableId
    private String tAppealId;

    /**
     * 损坏记录ID
     */
    private String damageId;

    /**
     * 附件ID
     */
    private String attachmentId;

    /**
     * 管理员ID
     */
    private String adminId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 交易ID
     */
    private String transactionId;

    /**
     * 租户水费ID
     */
    private String tenantWaterFeeId;

    /**
     * 租户电费ID
     */
    private String tenantElectricityFeeId;

    /**
     * 合同ID
     */
    private String contractId;

    /**
     * 申诉类型
     */
    private String tAppealType;

    /**
     * 申诉描述
     */
    private String tDescription;

    /**
     * 申诉状态
     */
    private String tStatus;

    /**
     * 申诉结果
     */
    private String tResult;

    /**
     * 申诉结果说明
     */
    private String tResultDescription;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}