package cn.edu.zjut.controller;

import cn.edu.zjut.entity.DamageAssessments.DTO.CreateDamageAssessmentDTO;
import cn.edu.zjut.entity.DamageAssessments.DTO.FindDamage_assessmentsDTO;
import cn.edu.zjut.entity.DamageAssessments.DTO.TanentFindDamage_assessmentsDTO;
import cn.edu.zjut.entity.DamageAssessments.DamageAssessments;
import cn.edu.zjut.entity.DamageAssessments.req.TenantConfirmDamage_Req;
import cn.edu.zjut.entity.DamageAssessments.resp.FindDamage_assessmentsResp;
import cn.edu.zjut.entity.DamageAssessments.resp.GetDamage_assessmentsResp;
import cn.edu.zjut.entity.DamageAssessments.resp.TanentFindDamage_assessmentsResp;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.DamageAssessmentsService;
import cn.edu.zjut.utils.UserInfoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/damage-assessment")
@Tag(name = "定损管理", description = "定损相关的 API")
public class DamageAssessmentController {

    private final DamageAssessmentsService damageAssessmentsService;


    // 创建定损
    @Operation(summary = "创建定损信息和相关附件", description = "创建定损信息和相关附件")
    @PostMapping("/createDamageAssessments")
    public CommonResult<Void> createDamageAssessments(@RequestBody @Valid CreateDamageAssessmentDTO createDamageAssessmentDTO) {

        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        //房东id
        String landlordid=userTokenInfoDto.getUserId();

        // 1. 验证传入的 DTO 是否有效（通过 @Valid 注解在前端校验）
        if (createDamageAssessmentDTO == null) {
            return CommonResult.error("请求数据为空");
        }

        System.out.println(createDamageAssessmentDTO.getHouseId());
        System.out.println(1);


        createDamageAssessmentDTO.setLandlordId(landlordid);

        try {
            damageAssessmentsService.createDamageAssessmentsAndAttachments(createDamageAssessmentDTO);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);

    }


    @Operation(summary = "房东查看定损信息", description = "查看定损信息")
    @PostMapping("/findDamageAssessments")
    public CommonResult<FindDamage_assessmentsResp> findDamageAssessments() {

        FindDamage_assessmentsResp findDamage_assessmentsResp;
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        //房东id
        String landlord=userTokenInfoDto.getUserId();


        // 1. 验证传入的 DTO 是否有效（通过 @Valid 注解在前端校验）
        if (landlord == null) {
            return CommonResult.error("请求数据为空");
        }
        try {
            findDamage_assessmentsResp=damageAssessmentsService.findDamageAssessments(landlord);

        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(findDamage_assessmentsResp);

    }


    // 修改定损
    @Operation(summary = "房东修改定损信息", description = "修改定损信息")
    @PostMapping("/updateDamageAssessments")
    public CommonResult<CreateDamageAssessmentDTO> updateDamageAssessments(@RequestBody @Valid CreateDamageAssessmentDTO createDamageAssessmentDTO) {
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        //房东id
        String landlordid = userTokenInfoDto.getUserId();
        System.out.println(landlordid);

        // 1. 验证传入的 DTO 是否有效（通过 @Valid 注解在前端校验）
        if (createDamageAssessmentDTO == null) {
            return CommonResult.error("请求数据为空");
        }
        createDamageAssessmentDTO.setLandlordId(landlordid);

        try {
            // 传入DTO进行修改操作
            damageAssessmentsService.updateDamageAssessments(createDamageAssessmentDTO);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }

    @Operation(summary = "房东删除定损信息", description = "删除定损信息")
    @PostMapping("/deleteDamageAssessments")
    public CommonResult<CreateDamageAssessmentDTO> updateDamageAssessments(@RequestBody @Valid String damageId) {
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        //房东id
        String userid = userTokenInfoDto.getUserId();

        try {
            // 传入DTO进行修改操作
            damageAssessmentsService.deleteDamageAssessments(damageId);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }



//    @Operation(summary = "房东获取所有定损信息", description = "获取所有定损信息列表")
//    @GetMapping("/getDamageAssessments")
//    public CommonResult<GetDamage_assessmentsResp> getDamageAssessments() {
//        GetDamage_assessmentsResp getDamage_assessmentsResp;
//        // 获取房东的定损信息
//        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
//        //房东id
//        String landlordid=userTokenInfoDto.getUserId();
//        // 1. 验证传入的 DTO 是否有效（通过 @Valid 注解在前端校验）
//        if (landlordid == null) {
//            return CommonResult.error("请求数据为空");
//        }
//        List<DamageAssessments> list = new ArrayList<>();
//        try {
//            getDamage_assessmentsResp=damageAssessmentsService.getDamageAssessments(landlordid);
//
//        } catch (Exception e) {
//            return CommonResult.error(e.getMessage());
//        }
//        return CommonResult.success(getDamage_assessmentsResp);
//    }


    @Operation(summary = "租客查看定损信息", description = "查看定损信息")
    @PostMapping("/tenantfindDamageAssessments")
    public CommonResult<TanentFindDamage_assessmentsResp> tenantfindDamageAssessments() {

        TanentFindDamage_assessmentsResp findDamage_assessmentsResp;
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        //租客id
        String tenantid=userTokenInfoDto.getUserId();

        System.out.println("tenantid"+tenantid);
        // 1. 验证传入的 DTO 是否有效（通过 @Valid 注解在前端校验）

        if (tenantid == null) {
            return CommonResult.error("请求数据为空");
        }

        try {
            findDamage_assessmentsResp=damageAssessmentsService.find_tenant_DamageAssessments(tenantid);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(findDamage_assessmentsResp);

    }


    // 租客确认定损信息
    @Operation(summary = "租客确认定损信息", description = "租客确认某天的定损信息")
    @PostMapping("/confirmDamageAssessment")
    public CommonResult<Void> tenantConfirmDamageAssessment(@RequestBody TenantConfirmDamage_Req tenantConfirmDamage_Req) {
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        //租客id
        String tenantid=userTokenInfoDto.getUserId();
        tenantConfirmDamage_Req.setTenantId(tenantid);
        try {
            // 调用服务层方法处理确认操作
            damageAssessmentsService.confirmDamageAssessment(tenantConfirmDamage_Req);
            return CommonResult.success(null);  // 返回成功
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }





}
