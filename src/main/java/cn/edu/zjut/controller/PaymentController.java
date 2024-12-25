package cn.edu.zjut.controller;

import cn.edu.zjut.entity.TenantProfile.req.TenantPaymentReq;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordPaymentReq;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.TenantProfileService;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.utils.UserInfoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 支付控制器
 * 用于大学生租户和房东修改余额
 * @Author bc
 * @Date 2024/12/25
 */
@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
@Tag(name = "支付管理", description = "支付相关的 API")
public class PaymentController {

    private final TenantProfileService tenantProfileService;
    private final LandlordProfileService landlordProfileService;

    @Operation(summary = "大学生租户充值或支付")
    @PostMapping("/tenant")
    public CommonResult<Void> tenantPayment(@RequestBody TenantPaymentReq req) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            tenantProfileService.modifyBalance(userTokenInfoDto.getUserId(), req.getAmount());
        } catch (Exception e) {
            log.error("Tenant Payment Error", e);
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }

    @Operation(summary = "房东充值或支付")
    @PostMapping("/landlord")
    public CommonResult<Void> landlordPayment(@RequestBody LandlordPaymentReq req) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            System.out.println("房东充值或支付-房东id:"+userTokenInfoDto.getUserId());
            landlordProfileService.modifyBalance(userTokenInfoDto.getUserId(), req.getAmount());
        } catch (Exception e) {
            log.error("Landlord Payment Error", e);
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
}
