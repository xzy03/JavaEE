package cn.edu.zjut.controller;

import cn.edu.zjut.annotation.PassAuthentication;
import cn.edu.zjut.entity.House.req.QueryHouseReq;
import cn.edu.zjut.entity.House.resp.HouseListInfo;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.entity.TenantProfile.req.*;
import cn.edu.zjut.entity.TenantProfile.resq.TenantListInfo;
import cn.edu.zjut.entity.TenantProfile.resq.TenantLoginResp;
import cn.edu.zjut.entity.admins.req.PwdChangeReq;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.TenantProfileService;
import cn.edu.zjut.utils.UserInfoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/tenant")
@Tag(name = "大学生租户管理", description = "大学生租户相关的 API")
public class TenantController {
     private final TenantProfileService tenantProfileService;
     @PassAuthentication
     @Operation(summary="大学生租户注册")
     @PostMapping("/register")
     public CommonResult<Void> register(@Validated @RequestBody TenantRegisterReq req) {
         try {
             tenantProfileService.registerTenant(req);
         } catch (Exception e) {
             return CommonResult.error(e.getMessage());
         }
         return CommonResult.success(null);
     }
    @PassAuthentication
    @Operation(summary="大学生租户登录")
    @PostMapping("/login")
    public CommonResult<TenantLoginResp> login(@Validated @RequestBody TenantLoginReq req) {
        TenantLoginResp tenantLoginResp;
        try {
            tenantLoginResp = tenantProfileService.loginTenant(req);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(tenantLoginResp);
    }
    @Operation(summary="查看大学生租户信息")
    @PostMapping("/tenantInfo")
    public CommonResult<TenantProfile> getTenantProfile() {
        TenantProfile tenantProfile;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            tenantProfile = tenantProfileService.getById(userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(tenantProfile);
    }
    @Operation(summary="修改大学生租户信息")
    @PostMapping("/updateTenantInfo")
    public CommonResult<TenantProfile> updateTenantProfile(@Validated @RequestBody TenantUpdateReq req) {
        TenantProfile tenantProfile;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            tenantProfile = tenantProfileService.updateTenateProfile(req, userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(tenantProfile);
    }
    @Operation(summary="大学生租户找回密码")
    @PostMapping("/findPwd")
    public CommonResult<Void> findPwd(@Validated @RequestBody PwdChangeReq req) {
        try {
            tenantProfileService.findPwd(req);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @Operation(summary="大学生租户身份证验证")
    @PostMapping("/idCardCheck")
    public CommonResult<Void> idCardCheck(
            @RequestParam("tCardNumber") String tCardNumber,
            @RequestParam("tName") String tName,
            @RequestParam("tSex") String tSex,
            @RequestParam("tBirth") String tBirth,
            @RequestParam("tCardImageFront") MultipartFile tCardImageFront,
            @RequestParam("tCardImageBack") MultipartFile tCardImageBack) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(tBirth);
            TenantIdcardReq req = new TenantIdcardReq(tCardNumber, tName, tSex, date,tCardImageFront, tCardImageBack);
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            tenantProfileService.idCardCheck(req, userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @Operation(summary="大学生租户学生认证信息")
    @PostMapping("/studentInfo")
    public CommonResult<Void> studentInfo(
            @RequestParam("tUniversity") String tUniversity,
            @RequestParam("tMajor") String tMajor,
            @RequestParam("tProfilePicture") MultipartFile tProfilePicture) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            tenantProfileService.studentInfo(tUniversity, tMajor, tProfilePicture,userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @Operation(summary="查看大学生列表")
    @PostMapping("/getTenantList")
    public CommonResult<TenantListInfo> getTenantList(@Validated @RequestBody QueryTenantReq req) {
        TenantListInfo tenantListInfo;
        try {
            tenantListInfo = tenantProfileService.getTenantList(req);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(tenantListInfo);
    }
}
