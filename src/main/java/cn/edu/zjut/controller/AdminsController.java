package cn.edu.zjut.controller;

import cn.edu.zjut.annotation.PassAuthentication;
import cn.edu.zjut.entity.admins.req.AdminsInfoReq;
import cn.edu.zjut.entity.admins.req.AdminsLoginReq;
import cn.edu.zjut.entity.admins.req.AdminsRegisterReq;
import cn.edu.zjut.entity.admins.req.PwdChangeReq;
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
    public CommonResult<Void> idCardCheck(String tenantId) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            adminsService.idCardCheck(tenantId);
        } catch (BusiException e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
}
