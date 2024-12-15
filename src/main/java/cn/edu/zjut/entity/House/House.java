package cn.edu.zjut.entity.House;

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
 * 房源信息表
 * @TableName house
 */
@TableName(value ="house")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class House implements Serializable {
    /**
     * 房屋ID，唯一标识
     */
    @TableId
    private String houseId;

    /**
     * 小区ID
     */
    private String communityId;

    /**
     * 房屋标题
     */
    private String hTitle;

    /**
     * 房屋位置
     */
    private String hLocation;

    /**
     * 租金
     */
    private BigDecimal hRent;

    /**
     * 房屋面积
     */
    private BigDecimal hArea;

    /**
     * 房间数量
     */
    private Integer hRooms;

    /**
     * 房屋状态
     */
    private String hStatus;

    /**
     * 可用日期
     */
    private Date hAvailableFrom;

    /**
     * 房屋朝向
     */
    private String hDirection;

    /**
     * 楼层
     */
    private Integer hFloor;

    /**
     * 总楼层数
     */
    private Integer hTotalFloors;

    /**
     * 房屋设施
     */
    private String hFacilities;

    /**
     * 是否允许宠物
     */
    private Integer hPetFriendly;

    /**
     * 要求租户
     */
    private String hTenantrequired;

    /**
     * 总租户数
     */
    private Integer hTotalTenants;

    /**
     * 剩余空缺
     */
    private Integer hRemainingVacancies;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}