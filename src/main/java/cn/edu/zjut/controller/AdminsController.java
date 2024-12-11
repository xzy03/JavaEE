package cn.edu.zjut.controller;

import cn.edu.zjut.entity.admins.Admins;
import cn.edu.zjut.entity.admins.req.AdminsLoginReq;
import cn.edu.zjut.entity.admins.req.AdminsRegisterReq;
import cn.edu.zjut.entity.admins.resp.AdminsLoginResp;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.AdminsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员控制器
 * @Author 项郑毅
 * @Date 2024/12/11
 */
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
        adminsService.registerAdmin(req);
        return CommonResult.success(null);
    }
    @Operation(summary="管理员用户登录")
    @PostMapping("/login")
    public CommonResult<AdminsLoginResp> login(@Validated @RequestBody AdminsLoginReq req) {
        AdminsLoginResp adminsLoginResp = adminsService.login(req);
        return CommonResult.success(adminsLoginResp);
    }
}
