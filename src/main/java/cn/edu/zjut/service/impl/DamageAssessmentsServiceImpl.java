package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.DamageAssessments.DTO.CreateDamageAssessmentDTO;
import cn.edu.zjut.entity.DamageAssessments.DTO.FindDamage_assessmentsDTO;
import cn.edu.zjut.entity.DamageAssessments.DTO.TanentFindDamage_assessmentsDTO;
import cn.edu.zjut.entity.DamageAssessments.DamageAssessments;
import cn.edu.zjut.entity.DamageAssessments.req.TenantConfirmDamage_Req;
import cn.edu.zjut.entity.DamageAssessments.resp.FindDamage_assessmentsResp;
import cn.edu.zjut.entity.DamageAssessments.resp.GetDamage_assessmentsResp;
import cn.edu.zjut.entity.DamageAssessments.resp.TanentFindDamage_assessmentsResp;
import cn.edu.zjut.entity.DamageAttachments.Attachments;
import cn.edu.zjut.entity.DamageAttachments.DamageAttachments;
import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.mapper.DamageAssessmentsMapper;
import cn.edu.zjut.mapper.DamageAttachmentsMapper;
import cn.edu.zjut.mapper.HouseMapper;
import cn.edu.zjut.service.DamageAssessmentsService;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.service.TenantProfileService;
import cn.edu.zjut.utils.IdGeneratorUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* @author 86173
* @description 针对表【damage_assessments(定损表)】的数据库操作Service实现
* @createDate 2024-12-12 23:49:20
*/
@Service
@Slf4j
public class DamageAssessmentsServiceImpl extends ServiceImpl<DamageAssessmentsMapper, DamageAssessments> implements DamageAssessmentsService {
    @Lazy
    @Resource
    DamageAssessmentsService damageAssessmentsService;

    @Resource
    private DamageAssessmentsMapper damageAssessmentsMapper;

    @Resource
    private DamageAttachmentsMapper damageAttachmentsMapper;

    @Resource
    private HouseMapper houseMapper;


    @Resource
    private TenantProfileService tenantProfileService;

    @Resource
    private LandlordProfileService landlordProfileService;

    /**
     * 创建定损信息及相关附件
     *
     * @param createDamageAssessmentDTO
     */
    @Transactional
    public void createDamageAssessmentsAndAttachments(CreateDamageAssessmentDTO createDamageAssessmentDTO) {
        // 自动生成 damageId
        IdGeneratorUtil idGeneratorUtil = new IdGeneratorUtil();

        //创建
        String damageId = idGeneratorUtil.nextUUID(new DamageAssessments());
        String attachmentId = idGeneratorUtil.nextUUID(new Attachments());
        System.out.println(attachmentId);

        // 创建定损附件
        DamageAttachments damageAttachments = createDamageAssessmentDTO.convertToDamageAttachment(createDamageAssessmentDTO);
        damageAttachments.setAttachmentId(attachmentId);  // 确保附件的 damageId 与定损信息一致
        damageAttachmentsMapper.insert(damageAttachments);


        // 创建定损信息
        DamageAssessments damageAssessments = createDamageAssessmentDTO.convertToDamageAssessment(createDamageAssessmentDTO);
        damageAssessments.setDamageId(damageId);
        damageAssessments.setAttachmentId(attachmentId);
        damageAssessments.setDaCreatedAt(new Date());
        damageAssessmentsMapper.insert(damageAssessments);

    }



    //房东查看定损信息
    @Override
    public FindDamage_assessmentsResp findDamageAssessments(String landlordId){
        List<FindDamage_assessmentsDTO> damageDetails = damageAssessmentsMapper.findBytenantId(landlordId);

        if (damageDetails != null && !damageDetails.isEmpty()) {
            // 返回成功响应，包含查询结果
            return new FindDamage_assessmentsResp(damageDetails);
        } else {

            // 返回没有找到数据的响应
            return new FindDamage_assessmentsResp(null);
        }
    }


    //租户查看定损信息
    @Override
    public TanentFindDamage_assessmentsResp find_tenant_DamageAssessments(String tenantid){
        List<TanentFindDamage_assessmentsDTO> damageDetails = damageAssessmentsMapper.tenantfindBytenantId(tenantid);

        System.out.println(tenantid);

        if (damageDetails != null && !damageDetails.isEmpty()) {

            // 返回成功响应，包含查询结果
            return new TanentFindDamage_assessmentsResp(damageDetails);
        } else {
            // 返回没有找到数据的响应
            return new TanentFindDamage_assessmentsResp(null);
        }
    }


    //房东删除定损的信息
    /**
     * 删除定损信息及相关的附件
     *
     * @param damageId 定损信息的 ID
     */
    @Transactional
    public void deleteDamageAssessments(String damageId) {
        try {
            System.out.println(damageId);

            String attachmentId = damageAssessmentsMapper.selectAttachmentIdByDamageId(damageId);
            //String attachmentId = damageAssessmentsMapper.selectByDamageId(damageId).getAttachmentId();
            System.out.println(attachmentId);
            // 1. 删除与定损信息关联的附件记录

            System.out.println(4);

            damageAssessmentsMapper.delectByDamageId(damageId);
            damageAttachmentsMapper.deleteByAttachId(attachmentId);
            System.out.println(2);



        } catch (Exception e) {
            // 异常会导致事务回滚，确保数据一致性
            throw new RuntimeException("删除定损信息及附件失败: " + e.getMessage());
        }
    }








    @Override
    @Transactional
    public void updateDamageAssessments(CreateDamageAssessmentDTO createDamageAssessmentDTO) {
        // 1. 获取要修改的 damageId
        String damageId = createDamageAssessmentDTO.getDamageId();

        // 2. 根据 damageId 查找现有的定损记录
        DamageAssessments existingDamageAssessment = damageAssessmentsMapper.selectByDamageId(damageId);
        if (existingDamageAssessment == null) {
            throw new RuntimeException("定损记录不存在，无法修改");
        }

        // 将 DTO 转换为 DamageAssessments 对象，并保留原有的 damageId
        DamageAssessments damageAssessments = createDamageAssessmentDTO.convertToDamageAssessment(createDamageAssessmentDTO);
        damageAssessments.setDamageId(damageId);  // 保证更新操作使用原来的 damageId

        // 如果附件信息需要更新，查找并更新附件
        String attachmentId = damageAssessments.getAttachmentId();
        if (attachmentId != null) {
            DamageAttachments existingAttachment = damageAttachmentsMapper.selectByAttachmentId(attachmentId);
            if (existingAttachment != null) {
                // 如果附件存在，可以更新附件的相关信息
                DamageAttachments updatedAttachment = createDamageAssessmentDTO.convertToDamageAttachment(createDamageAssessmentDTO);
                updatedAttachment.setAttachmentId(attachmentId);  // 确保附件ID一致
                damageAttachmentsMapper.updateById(updatedAttachment);
            } else {
                // 如果附件不存在，可以选择是否创建新的附件
                String newAttachmentId = new IdGeneratorUtil().nextUUID(new Attachments());
                DamageAttachments newAttachment = createDamageAssessmentDTO.convertToDamageAttachment(createDamageAssessmentDTO);
                newAttachment.setAttachmentId(newAttachmentId);  // 新的附件ID
                damageAttachmentsMapper.insert(newAttachment);  // 插入新的附件
            }
        }

        // 5. 更新定损记录
        damageAssessments.setDaCreatedAt(new Date());  // 设置更新时间
        damageAssessmentsMapper.updateById(damageAssessments);

        // 6. 事务成功后返回
    }



    //用户确认相关的定损信息
    @Override
    @Transactional // 保证整个操作原子性
    public void confirmDamageAssessment(TenantConfirmDamage_Req tenantConfirmReq) {
        // 根据 damageId 查找定损记录
        DamageAssessments damageAssessments = damageAssessmentsMapper
                .findDamageAssessmentByDamageId(tenantConfirmReq.getDamageId());

        if (damageAssessments != null) {
            // 校验租户余额是否足够
            BigDecimal damageAmount = damageAssessments.getDaAmount(); // 获取定损金额
            String tenantId = tenantConfirmReq.getTenantId();

            // 获取租户信息并检查余额
            TenantProfile tenantProfile = tenantProfileService.getTenantProfileById(tenantId);


            if (tenantProfile == null) {
                throw new RuntimeException("租户信息不存在");
            }

            BigDecimal tenantBalance = tenantProfile.getTBalance();
            if (tenantBalance.compareTo(damageAmount) < 0) {
                // 如果余额不足，抛出异常
                throw new RuntimeException("租户余额不足，无法确认定损");
            }

            // 扣除租户余额
            tenantProfile.setTBalance(tenantBalance.subtract(damageAmount));
            boolean isBalanceUpdated = tenantProfileService.updateTenantProfileBalance(tenantProfile.getTenantId(),tenantProfile.getTBalance());
            if (!isBalanceUpdated) {
                throw new RuntimeException("更新租户余额失败");
            }
            System.out.println(1);

            // 通过 damageId 查找房东信息
            String landlordId = damageAssessments.getLandlordId();  // 获取房东ID
            // 获取房东信息
            LandlordProfile landlordProfile = landlordProfileService.getLandlordProfileById(landlordId);
            if (landlordProfile == null) {
                throw new RuntimeException("房东信息不存在");
            }

            System.out.println(2);

            // 获取房东余额并增加相应金额
            BigDecimal landlordBalance = landlordProfile.getLBalance();
            landlordProfile.setLBalance(landlordBalance.add(damageAmount));
            boolean isLandlordBalanceUpdated = landlordProfileService.updateLandlordProfile(landlordProfile);
            if (!isLandlordBalanceUpdated) {
                throw new RuntimeException("更新房东余额失败");
            }

            System.out.println(3);

            // 更新定损状态为已确认
            String newStatus = tenantConfirmReq.getDaStatus();  // 获取确认状态（如 confirmed）
            damageAssessments.setDaStatus(newStatus);  // 设置新的状态
            damageAssessmentsMapper.updateDamageAssessmentStatus(damageAssessments.getDamageId(), newStatus);
        } else {
            // 如果没有找到定损信息，抛出异常
            throw new RuntimeException("定损信息未找到！");
        }
    }




    public GetDamage_assessmentsResp getDamageAssessments(String userid){
        List<DamageAssessments> damageAssessmentsList = damageAssessmentsMapper.findBytenantId1(userid);
        GetDamage_assessmentsResp getDamage_assessmentsResp=new GetDamage_assessmentsResp(damageAssessmentsList);
        return getDamage_assessmentsResp;
    }



}




