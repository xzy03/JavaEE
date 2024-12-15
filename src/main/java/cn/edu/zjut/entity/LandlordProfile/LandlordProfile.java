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
     * 房东ID，唯一标识
     */
    @TableId
    private String landlordId;

    /**
     * 房东房产ID
     */
    private String lPropertyId;

    /**
     * 房东账户
     */
    private String lAccount;

    /**
     * 房东密码
     */
    private String lPassword;

    /**
     * 房东电话号码
     */
    private String lPhoneNumber;

    /**
     * 房东电子邮件
     */
    private String lEmail;

    /**
     * 房东房屋状态
     */
    private String lHouseStatus;

    /**
     * 房东房屋许可证照片
     */
    private String lHouseLicensePhoto;

    /**
     * 房东房屋许可证状态
     */
    private String lHouseLicenseState;

    /**
     * 房东头像
     */
    private String lProfilePicture;

    /**
     * 房东身份证号
     */
    private String lCardNumber;

    /**
     * 房东姓名
     */
    private String lName;

    /**
     * 房东身份证正面照
     */
    private String lCardImageFront;

    /**
     * 房东身份证背面照
     */
    private String lCardImageBack;

    /**
     * 房东状态
     */
    private String lStatus;

    /**
     * 房东余额
     */
    private BigDecimal lBalance;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}