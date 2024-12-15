package cn.edu.zjut.entity.Appointments;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 预约看房记录表
 * @TableName appointments
 */
@TableName(value ="appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointments implements Serializable {
    /**
     * 预约ID，唯一标识
     */
    @TableId
    private String appointmentId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 预约日期时间
     */
    private Date appointmentDate;

    /**
     * 预约状态
     */
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}