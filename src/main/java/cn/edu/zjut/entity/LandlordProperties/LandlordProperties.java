package cn.edu.zjut.entity.LandlordProperties;

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
 * 房东租赁房屋表
 * @TableName landlord_properties
 */
@TableName(value ="landlord_properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LandlordProperties implements Serializable {
    /**
     * 房东房产ID，唯一标识
     */
    @TableId
    private String ltPropertyId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 房东房产状态
     */
    private String ltStatus;

    /**
     * 房东房产开始日期
     */
    private Date ltStartDate;

    /**
     * 房东房产结束日期
     */
    private Date ltEndDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}