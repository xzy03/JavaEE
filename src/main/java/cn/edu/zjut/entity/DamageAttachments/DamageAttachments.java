package cn.edu.zjut.entity.DamageAttachments;

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
 * 定损附件表
 * @TableName damage_attachments
 */
@TableName(value ="damage_attachments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DamageAttachments implements Serializable {
    /**
     * 附件ID，唯一标识
     */
    @TableId
    private String attachmentId;

    /**
     * 附件类型
     */
    private String daType;

    /**
     * 附件URL地址
     */
    private String daUrl;

    /**
     * 附件顺序
     */
    private Integer daOrder;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}