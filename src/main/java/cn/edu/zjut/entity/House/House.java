package cn.edu.zjut.entity.House;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * 房源信息表
 * @TableName house
 */
@TableName(value ="house")
@Data
@Builder
public class House implements Serializable {
    /**
     * 房源ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String houseId;

    /**
     * 小区ID
     */
    private String communityId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 房源标题
     */
    private String hTitle;

    /**
     * 房源位置
     */
    private String hLocation;

    /**
     * 租金
     */
    private BigDecimal hRent;

    /**
     * 面积（平方米）
     */
    private BigDecimal hArea;

    /**
     * 房间布局
     */
    private String hRooms;

    /**
     * 可入住时间
     */
    private Date hAvailableFrom;

    /**
     * 朝向
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
     * 配套设施
     */
    private String hFacilities;

    /**
     * 是否允许宠物
     */
    private Integer hPetFriendly;

    /**
     * 租客要求
     */
    private String hTenantrequired;

    /**
     * 总租户数量
     */
    private Integer hTotalTenants;

    /**
     * 剩余空闲数量
     */
    private Integer hRemainingVacancies;

    /**
     * 房产证图片
     */
    private String lHouseLicensePhoto;

    /**
     * 房产证验证状态
     */
    private String lHouseLicenseState;

    /**
     * 房屋图片
     */
    private String lHousePhoto;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}