package cn.edu.zjut.controller;

import cn.edu.zjut.entity.Transactions.req.TransactionIdReq;
import cn.edu.zjut.entity.Transactions.resp.DepositListInfo;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.TransactionsService;
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
@RequestMapping("/deposit")
@Tag(name = "押金管理", description = "押金管理相关的 API")
public class DepositController {
    private final TransactionsService depositService;
    @Operation(summary = "大学生租户查看押金支付记录")
    @PostMapping("/viewTenantDeposit")
    public CommonResult<DepositListInfo> viewTenantDeposit() {
        DepositListInfo depositListInfo;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            depositListInfo = depositService.viewTenantDeposit(userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(depositListInfo);
    }
    @Operation(summary = "房东查看所有押金支付记录")
    @PostMapping("/viewLandlordDeposit")
    public CommonResult<DepositListInfo> viewLandlordDeposit() {
        DepositListInfo depositListInfo;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            depositListInfo = depositService.viewLandlordDeposit(userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(depositListInfo);
    }
    @Operation(summary = "大学生租户支付押金")
    @PostMapping("/payDeposit")
    public CommonResult<Void> payDeposit(@Validated @RequestBody TransactionIdReq req) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            depositService.payDeposit(req.getTransactionId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
}
