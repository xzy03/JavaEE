package cn.edu.zjut.mapper;

import cn.edu.zjut.entity.DamageAssessments.DTO.FindDamage_assessmentsDTO;
import cn.edu.zjut.entity.DamageAssessments.DTO.TanentFindDamage_assessmentsDTO;
import cn.edu.zjut.entity.DamageAssessments.DamageAssessments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author 86173
* @description 针对表【damage_assessments(定损表)】的数据库操作Mapper
* @createDate 2024-12-12 23:49:20
* @Entity cn.edu.zjut.entity.DamageAssessments.DamageAssessments
*/
public interface DamageAssessmentsMapper extends BaseMapper<DamageAssessments> {

    // 通过房东ID来查找相应的定损表中的信息
    @Select("SELECT " +
            "da.damage_id AS damageId, " +
            "da.house_id AS houseId, " +
            "h.h_title AS hTitle, " +
            "da.tenant_id AS tenantId, " +
            "t.t_name AS tName, " +
            "da.da_name AS daName, " +
            "da.da_amount AS daAmount, " +
            "da.da_status AS daStatus " +

            "FROM damage_assessments da " +
            "JOIN house h ON da.house_id = h.house_id " +
            "JOIN tenant_profile t ON da.tenant_id = t.tenant_id " +
            "WHERE da.landlord_id = #{landlordId}")
    List<FindDamage_assessmentsDTO> findBytenantId(String landlordId);

    // 通过租客ID来查找所有有关定损的信息
    @Select("SELECT " +
            "da.damage_id AS damageId, " +       // 对应DTO类中的damageId
            "lp.l_name AS lName, " +            // 对应DTO类中的lName
            "da.house_id AS houseId, " +        // 对应DTO类中的houseId
            "h.h_title AS hTitle, " +           // 对应DTO类中的hTitle
            "da.tenant_id AS tenantId, " +      // 对应DTO类中的tenantId
            "t.t_name AS tName, " +             // 对应DTO类中的tName
            "da.da_name AS daName, " +          // 对应DTO类中的daName
            "da.da_amount AS daAmount, " +      // 对应DTO类中的daAmount
            "da.da_status AS daStatus " +       // 对应DTO类中的daStatus
            "FROM damage_assessments da " +
            "JOIN house h ON da.house_id = h.house_id " +
            "JOIN landlord_profile lp ON h.landlord_id = lp.landlord_id " +
            "JOIN tenant_profile t ON da.tenant_id = t.tenant_id " + // 根据租客ID筛选
            "WHERE da.tenant_id = #{tenantId}")
    List<TanentFindDamage_assessmentsDTO> tenantfindBytenantId(String tenantid);


    //通过定损的id来查找相应的数据表中的信息
    @Select("SELECT damage_id,house_id,tenant_id," +
            "        attachment_id,landlord_id,da_name," +
            "        da_amount,da_description,da_status," +
            "        da_created_at " +
            "FROM damage_assessments da " +
            "WHERE da.damage_id = #{damageId}")
    DamageAssessments findDamageAssessmentByDamageId(String damageId);


    //房东通过租户的id来查找到相关的全部定损信息
    @Select("SELECT " +
            "damage_id AS damageId, " +
            "house_id AS houseId, " +
            "tenant_id AS tenantId, " +
            "h_title AS hTitle, " +
            "da_name AS daName, " +
            "da_amount AS daAmount, " +
            "da_description AS daDescription, " +
            "da_status AS daStatus, " +
            "da_created_at AS daCreatedAt " +
            "FROM damage_assessments " +
            "WHERE tenant_id = #{tenantId}")
    public List<DamageAssessments> findBytenantId1(@Param("tenantId") String tenantId);

    //房东通过damageid来删除定损信息



    //租户确认定损信息
    @Update("UPDATE damage_assessments " +
            "SET da_status = #{daStatus} " +
            "WHERE damage_id = #{damageId} AND tenant_id = #{tenantId}")
    void updateDamageAssessmentStatus(@Param("damageId") String damageId,
                                      @Param("daStatus") String daStatus);


    @Select(
            "SELECT * FROM damage_assessments " +
                    "WHERE damage_id = #{damageId}")
    DamageAssessments selectByDamageId( @Param("damageId") String damageId);

    @Select("SELECT attachment_id " +
            "FROM damage_assessments " +
            "WHERE damage_id = #{damageId}")
    String selectAttachmentIdByDamageId( @Param("damageId") String damageId);

    @Delete("DELETE FROM damage_assessments WHERE damage_id = #{damageId}")
    void delectByDamageId( @Param("damageId")String damageId);



}




