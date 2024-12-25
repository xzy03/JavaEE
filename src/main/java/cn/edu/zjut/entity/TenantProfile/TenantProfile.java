package cn.edu.zjut.entity.TenantProfile;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 大学生租客表
 * @TableName tenant_profile
 */
@TableName(value ="tenant_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantProfile implements Serializable {
    /**
     * 租客ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String tenantId;

    /**
     * 学生账号
     */
    private String tAccount;

    /**
     * 学生密码
     */
    private String tPassword;

    /**
     * 就读大学
     */
    private String tUniversity;

    /**
     * 专业
     */
    private String tMajor;

    /**
     * 手机号
     */
    private String tPhoneNumber;

    /**
     * 邮箱
     */
    private String tEmail;

    /**
     * 身份认证状态
     */
    private String tIdentityStatus;

    /**
     * 学生证照片
     */
    private String tProfilePicture;

    /**
     * 身份证号码
     */
    private String tCardNumber;

    /**
     * 姓名
     */
    private String tName;

    /**
     * 身份证正面图片
     */
    private String tCardImageFront;

    /**
     * 身份证背面图片
     */
    private String tCardImageBack;

    /**
     * 学生证审核状态
     */
    private String tStatus;

    /**
     * 钱包余额
     */
    private BigDecimal tBalance;

    /**
     * 性别
     */
    private String tSex;

    /**
     * 出生年月
     */
    private Date tBirth;

    /**
     * 个人介绍
     */
    private String tIntroduction;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}