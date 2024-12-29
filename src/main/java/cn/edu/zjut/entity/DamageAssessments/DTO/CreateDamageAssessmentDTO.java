package cn.edu.zjut.entity.DamageAssessments.DTO;


import cn.edu.zjut.entity.DamageAssessments.DamageAssessments;
import cn.edu.zjut.entity.DamageAttachments.DamageAttachments;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CreateDamageAssessmentDTO {
    private String damageId;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 附件ID
     */
    //private String attachmentId;

    /**
     * 房东ID
     */
    private String landlordId;

    /**
     * 损坏评估名称
     */
    private String daName;

    /**
     * 损坏金额
     */
    private BigDecimal daAmount;

    /**
     * 损坏描述
     */
    private String daDescription;

    /**
     * 损坏评估状态
     */
    private String daStatus;

    /**
     * 评估创建时间
     */
    private Date daCreatedAt;

    private String daType;
    /**
     * 附件URL地址
     */
    private String daUrl;

    /**
     * 附件顺序
     */
    private Integer daOrder;

    public CreateDamageAssessmentDTO() {
    }

    public DamageAssessments convertToDamageAssessment(CreateDamageAssessmentDTO createDamageAssessmentDTO) {
        DamageAssessments damageAssessment = new DamageAssessments();
        damageAssessment.setDamageId(damageId);
        damageAssessment.setHouseId(houseId);
        damageAssessment.setTenantId(tenantId);
        //damageAssessment.setAttachmentId(attachmentId);
        damageAssessment.setLandlordId(landlordId);
        damageAssessment.setDaName(daName);
        damageAssessment.setDaAmount(daAmount);
        damageAssessment.setDaDescription(daDescription);
        damageAssessment.setDaStatus(daStatus);
        damageAssessment.setDaCreatedAt(daCreatedAt);
        return damageAssessment;

    }

    public DamageAttachments convertToDamageAttachment(CreateDamageAssessmentDTO createDamageAssessmentDTO) {
        DamageAttachments damageAttachment = new DamageAttachments();
        damageAttachment.setDaType(daType);
        damageAttachment.setDaUrl(daUrl);
        damageAttachment.setDaOrder(daOrder);
        return damageAttachment;

    }
}
