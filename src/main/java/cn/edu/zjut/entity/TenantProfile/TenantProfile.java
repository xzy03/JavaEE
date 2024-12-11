package cn.edu.zjut.entity.TenantProfile;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 大学生租客表
 * @TableName tenant_profile
 */
@TableName(value ="tenant_profile")
@Data
public class TenantProfile implements Serializable {
    /**
     * 租户ID，唯一标识
     */
    @TableId
    private String tenantId;

    /**
     * 租户账户
     */
    private String tAccount;

    /**
     * 租户密码
     */
    private String tPassword;

    /**
     * 租户所在大学
     */
    private String tUniversity;

    /**
     * 租户专业
     */
    private String tMajor;

    /**
     * 租户电话
     */
    private String tPhoneNumber;

    /**
     * 租户电子邮件
     */
    private String tEmail;

    /**
     * 租户身份状态
     */
    private String tIdentityStatus;

    /**
     * 租户头像
     */
    private String tProfilePicture;

    /**
     * 租户身份证号
     */
    private String tCardNumber;

    /**
     * 租户姓名
     */
    private String tName;

    /**
     * 租户身份证正面照
     */
    private String tCardImageFront;

    /**
     * 租户身份证背面照
     */
    private String tCatImageBack;

    /**
     * 租户状态
     */
    private String tStatus;

    /**
     * 租户余额
     */
    private BigDecimal tBalance;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}