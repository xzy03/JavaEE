package cn.edu.zjut.entity.admins;

import cn.edu.zjut.entity.admins.req.AdminsRegisterReq;
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
 * 管理员表
 * @TableName admins
 */
@TableName(value ="admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admins implements Serializable {
    /**
     * 管理员ID，唯一标识
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String adminId;

    /**
     * 管理员用户名
     */
    private String adUsername;

    /**
     * 管理员密码的哈希值
     */
    private String adPasswordHash;

    /**
     * 管理员电子邮件地址
     */
    private String adEmail;

    /**
     * 管理员电话号码
     */
    private String adPhone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Admins(AdminsRegisterReq req) {
        this.adUsername = req.getAdUsername();
        this.adPasswordHash = req.getAdPasswordHash();
        this.adEmail = req.getAdEmail();
        this.adPhone = req.getAdPhone();
    }
}