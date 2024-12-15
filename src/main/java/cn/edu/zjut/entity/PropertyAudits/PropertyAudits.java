package cn.edu.zjut.entity.PropertyAudits;

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
 * 房源审核表
 * @TableName property_audits
 */
@TableName(value ="property_audits")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyAudits implements Serializable {
    /**
     * 审计ID，唯一标识
     */
    @TableId
    private String auditId;

    /**
     * 管理员ID
     */
    private String adminId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 审计状态
     */
    private String paStatus;

    /**
     * 审计结果
     */
    private String paResult;

    /**
     * 审计结果描述
     */
    private String lResultDescription;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}