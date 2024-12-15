package cn.edu.zjut.entity.Posts;

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
 * 帖子表
 * @TableName posts
 */
@TableName(value ="posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Posts implements Serializable {
    /**
     * 帖子ID，唯一标识
     */
    @TableId
    private String postId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 帖子标题
     */
    private String pTitle;

    /**
     * 帖子内容
     */
    private String pContent;

    /**
     * 帖子状态
     */
    private String pStatus;

    /**
     * 帖子创建时间
     */
    private Date pCreatedAt;

    /**
     * 帖子更新时间
     */
    private Date pUpdatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}