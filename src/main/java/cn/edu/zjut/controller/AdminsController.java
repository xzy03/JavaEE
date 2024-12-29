package cn.edu.zjut.controller;

import cn.edu.zjut.annotation.PassAuthentication;
import cn.edu.zjut.entity.admins.req.*;
import cn.edu.zjut.entity.admins.resp.AdminsLoginResp;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.service.AdminsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cn.edu.zjut.utils.UserInfoUtils;

/**
 * 管理员控制器
 * @Author 项郑毅
 * @Date 2024/12/11
 */
@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
@Tag(name = "管理员管理", description = "管理员相关的 API")
public class AdminsController {
    private final AdminsService adminsService;
    @PassAuthentication
    @Operation(summary="管理员用户注册")
    @PostMapping("/register")
    public CommonResult<Void> register(@Validated @RequestBody AdminsRegisterReq req) {
        try {
            adminsService.registerAdmin(req);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @PassAuthentication
    @Operation(summary="管理员用户登录")
    @PostMapping("/login")
    public CommonResult<AdminsLoginResp> login(@Validated @RequestBody AdminsLoginReq req) {
        AdminsLoginResp adminsLoginResp;
        try {
            adminsLoginResp = adminsService.login(req);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(adminsLoginResp);
    }

    @Operation(summary="管理员用户修改信息")
    @PostMapping("/changeUserInfo")
    public CommonResult<Void> changeUserInfo(@Validated @RequestBody AdminsInfoReq req) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            adminsService.changeUserInfo(req, userTokenInfoDto.getUserId());
        } catch (BusiException e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @Operation(summary="管理员用户找回密码")
    @PostMapping("/findPwd")
    public CommonResult<Void> findPwd(@Validated @RequestBody PwdChangeReq req) {
        try {
            adminsService.findPwd(req);
        } catch (BusiException e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @Operation(summary="管理员审核大学生租户身份证")
    @PostMapping("/idCardCheck")
    public CommonResult<Void> idCardCheck(@Validated @RequestBody CheckReq req) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            adminsService.idCardCheck(req);
        } catch (BusiException e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @Operation(summary="管理员审核大学生租户学生证")
    @PostMapping("/studentCardCheck")
    public CommonResult<Void> studentCardCheck(@Validated @RequestBody CheckReq req) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            adminsService.studentCardCheck(req);
        } catch (BusiException e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @Operation(summary="管理员审核房东身份证")
    @PostMapping("/landlordIdCardCheck")
    public CommonResult<Void> landlordIdCardCheck(@Validated @RequestBody CheckReq req) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            adminsService.landlordIdCardCheck(req);
        } catch (BusiException e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @Operation(summary="管理员审核房东房产证")
    @PostMapping("/landlordHouseCardCheck")
    public CommonResult<Void> landlordHouseCardCheck(@Validated @RequestBody CheckReq req) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            adminsService.landlordHouseCardCheck(req);
        } catch (BusiException e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
}
