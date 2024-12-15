package cn.edu.zjut.entity.Landlordappeals;

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
 * 房东申诉表
 * @TableName landlordappeals
 */
@TableName(value ="landlordappeals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Landlordappeals implements Serializable {
    /**
     * 申诉ID，唯一标识
     */
    @TableId
    private byte[] lAppealId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 附件ID
     */
    private String attachmentId;

    /**
     * 采购订单ID
     */
    private String poOrderId;

    /**
     * 管理员ID
     */
    private String adminId;

    /**
     * 申诉类型
     */
    private String lAppealType;

    /**
     * 申诉描述
     */
    private String lDescription;

    /**
     * 申诉结果
     */
    private String lResult;

    /**
     * 申诉结果说明
     */
    private String lResultDescription;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}