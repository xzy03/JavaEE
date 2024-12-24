package cn.edu.zjut.controller;

import cn.edu.zjut.annotation.PassAuthentication;
import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordProfileLoginReq;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordProfileRegisterReq;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordProfileUpdateReq;
import cn.edu.zjut.entity.LandlordProfile.resp.LandlordProfileLoginResp;
import cn.edu.zjut.entity.TenantProfile.req.TenantIdcardReq;
import cn.edu.zjut.entity.admins.req.PwdChangeReq;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.utils.UserInfoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/landlords")
@Tag(name = "房东管理", description = "房东相关的 API")
public class landlordController {
    private final LandlordProfileService landlordsService;
    @Operation(summary="房东注册")
    @PostMapping("/register")
    public CommonResult<Void> register(@Validated @RequestBody LandlordProfileRegisterReq req) {
        try {
            landlordsService.registerLandlord(req);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @PassAuthentication
    @Operation(summary="房东登录")
    @PostMapping("/login")
    public CommonResult<LandlordProfileLoginResp> login(@Validated @RequestBody LandlordProfileLoginReq req) {
        LandlordProfileLoginResp landlordProfileLoginResp;
        try {
            landlordProfileLoginResp = landlordsService.loginLandlord(req);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(landlordProfileLoginResp);
    }

    @Operation(summary="查看房东信息")
    @GetMapping("/landlordInfo")
    public CommonResult<LandlordProfile> getLandlordProfile() {
        LandlordProfile landlordProfile;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            landlordProfile = landlordsService.getById(userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(landlordProfile);
    }
    @Operation(summary="修改房东信息")
    @PostMapping("/updateLandlordInfo")
    public CommonResult<LandlordProfile> updateLandlordProfile(@Validated @RequestBody LandlordProfileUpdateReq req) {
        LandlordProfile landlordProfile;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            landlordProfile = landlordsService.updateLandlordProfile(req, userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(landlordProfile);
    }
    @Operation(summary="房东用户找回密码")
    @PostMapping("/findPwd")
    public CommonResult<Void> findPwd(@Validated @RequestBody PwdChangeReq req) {
        try {
            landlordsService.findPwd(req);
        } catch (BusiException e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @Operation(summary="房东身份证验证")
    @PostMapping("/landlordIdCardCheck")
    public CommonResult<Void> landlordIdCardCheck(
            @RequestParam("tCardNumber") String tCardNumber,
            @RequestParam("tName") String tName,
            @RequestParam("tCardImageFront") MultipartFile tCardImageFront,
            @RequestParam("tCardImageBack") MultipartFile tCardImageBack) {
        TenantIdcardReq req = new TenantIdcardReq(tCardNumber, tName, tCardImageFront, tCardImageBack);
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            landlordsService.landlordIdCardCheck(req, userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
}
