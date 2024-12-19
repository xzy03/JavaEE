package cn.edu.zjut.entity.LandlordProfile;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 房东表
 * @TableName landlord_profile
 */
@TableName(value ="landlord_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LandlordProfile implements Serializable {
    /**
     * 房东ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String landlordId;

    /**
     * 房东账号
     */
    private String lAccount;

    /**
     * 房东密码
     */
    private String lPassword;

    /**
     * 手机号
     */
    private String lPhoneNumber;

    /**
     * 邮箱
     */
    private String lEmail;

    /**
     * 房东身份验证状态
     */
    private String lHouseStatus;

    /**
     * 房东头像图片的URL
     */
    private String lProfilePicture;

    /**
     * 身份证号码
     */
    private String lCardNumber;

    /**
     * 姓名
     */
    private String lName;

    /**
     * 身份证正面图片
     */
    private String lCardImageFront;

    /**
     * 身份证背面图片
     */
    private String lCardImageBack;

    /**
     * 审核状态
     */
    private String lStatus;

    /**
     * 钱包余额
     */
    private BigDecimal lBalance;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}