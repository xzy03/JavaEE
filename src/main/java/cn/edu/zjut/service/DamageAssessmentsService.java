package cn.edu.zjut.service;

import cn.edu.zjut.entity.DamageAssessments.DTO.CreateDamageAssessmentDTO;
import cn.edu.zjut.entity.DamageAssessments.DamageAssessments;
import cn.edu.zjut.entity.DamageAssessments.req.TenantConfirmDamage_Req;
import cn.edu.zjut.entity.DamageAssessments.resp.FindDamage_assessmentsResp;
import cn.edu.zjut.entity.DamageAssessments.resp.GetDamage_assessmentsResp;
import cn.edu.zjut.entity.DamageAssessments.resp.TanentFindDamage_assessmentsResp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86173
* @description 针对表【damage_assessments(定损表)】的数据库操作Service
* @createDate 2024-12-12 23:49:20
*/
public interface DamageAssessmentsService extends IService<DamageAssessments> {


    /**
     * 创建定损信息
     *
     * @param createDamageAssessmentDTO 定损信息对象
     * @return 是否成功
     */
    void createDamageAssessmentsAndAttachments(CreateDamageAssessmentDTO createDamageAssessmentDTO);

    public FindDamage_assessmentsResp findDamageAssessments(String userid);


    public TanentFindDamage_assessmentsResp find_tenant_DamageAssessments(String tenantid);

    GetDamage_assessmentsResp getDamageAssessments(String userid);

    void deleteDamageAssessments(String userid);

    void updateDamageAssessments(CreateDamageAssessmentDTO createDamageAssessmentDTO);

    void confirmDamageAssessment(TenantConfirmDamage_Req tenantConfirmReq);
}
