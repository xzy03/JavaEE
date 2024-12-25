package cn.edu.zjut.entity.Appointments;

import cn.edu.zjut.entity.Appointments.req.AppointmentsSubmitReq;
import cn.edu.zjut.controller.HouseController;
import cn.edu.zjut.entity.House.req.*;
import cn.edu.zjut.service.HouseService;
import cn.edu.zjut.mapper.HouseMapper;
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
    @TableId(type = IdType.ASSIGN_UUID)
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

    public Appointments(AppointmentsSubmitReq req,String tenantId,String landlordId) {
        // 设置 houseId
        this.houseId = req.getHouseId();

        // 使用 houseId 查询 landlordId
        this.landlordId = landlordId;

        this.tenantId=tenantId;

        // 其他字段赋值
        this.appointmentDate = req.getAppointmentDate();
        this.status = "待确认";
    }

}