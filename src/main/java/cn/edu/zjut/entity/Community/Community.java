package cn.edu.zjut.entity.Community;

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
 * 小区信息表
 * @TableName community
 */
@TableName(value ="community")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Community implements Serializable {
    /**
     * 小区ID，唯一标识
     */
    @TableId
    private String communityId;

    /**
     * 小区名称
     */
    private String comName;

    /**
     * 小区地址
     */
    private String comLocation;

    /**
     * 小区描述
     */
    private String comDescription;

    /**
     * 小区设施
     */
    private String comFacilities;

    /**
     * 小区房产总数
     */
    private Integer comPropertyCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}