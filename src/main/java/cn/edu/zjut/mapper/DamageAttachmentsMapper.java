package cn.edu.zjut.mapper;

import cn.edu.zjut.entity.DamageAttachments.DamageAttachments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author 86173
* @description 针对表【damage_attachments(定损附件表)】的数据库操作Mapper
* @createDate 2024-12-12 23:49:44
* @Entity cn.edu.zjut.entity.DamageAttachments.DamageAttachments
*/
public interface DamageAttachmentsMapper extends BaseMapper<DamageAttachments> {
    // 删除与定损信息关联的附件
    @Delete("DELETE FROM damage_attachments " +
            "WHERE attachment_id = #{attachmentId}")
    void deleteByAttachId(String attachmentId);

    // 查找与定损信息关联的附件
    @Select("Select *" +
            "FROM damage_attachments " +
            "WHERE attachment_id = #{attachmentId}")
    DamageAttachments selectByAttachmentId( String attachmentId);




}




