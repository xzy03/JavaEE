package cn.edu.zjut.controller;

import cn.edu.zjut.annotation.PassAuthentication;
import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.entity.TenantProfile.req.TenantLoginReq;
import cn.edu.zjut.entity.TenantProfile.req.TenantRegisterReq;
import cn.edu.zjut.entity.TenantProfile.req.TenantUpdateReq;
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

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/tenant")
@Tag(name = "大学生租户管理", description = "大学生租户相关的 API")
public class TenantController {
     private final TenantProfileService landlordsService;
     @Operation(summary="大学生租户注册")
     @PostMapping("/register")
     public CommonResult<Void> register(@Validated @RequestBody TenantRegisterReq req) {
         try {
             landlordsService.registerTenant(req);
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
            tenantLoginResp = landlordsService.loginTenant(req);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(tenantLoginResp);
    }
    @Operation(summary="查看大学生租户信息")
    @GetMapping("/tenantInfo")
    public CommonResult<TenantProfile> getTenantProfile() {
        TenantProfile tenantProfile;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            tenantProfile = landlordsService.getById(userTokenInfoDto.getUserId());
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
            tenantProfile = landlordsService.updateTenateProfile(req, userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(tenantProfile);
    }
    @Operation(summary="大学生租户找回密码")
    @PostMapping("/findPwd")
    public CommonResult<Void> findPwd(@Validated @RequestBody PwdChangeReq req) {
        try {
            landlordsService.findPwd(req);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
}
