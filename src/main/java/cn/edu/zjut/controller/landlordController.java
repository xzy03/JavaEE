package cn.edu.zjut.controller;

import cn.edu.zjut.annotation.PassAuthentication;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordProfileLoginReq;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordProfileRegisterReq;
import cn.edu.zjut.entity.LandlordProfile.resp.LandlordProfileLoginResp;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.LandlordProfileService;
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
}
