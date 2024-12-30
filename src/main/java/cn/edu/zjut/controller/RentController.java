package cn.edu.zjut.controller;

import cn.edu.zjut.entity.Transactions.req.TransactionIdReq;
import cn.edu.zjut.entity.Transactions.resp.RentListInfo;
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
@RequestMapping("/rent")
@Tag(name = "房租管理", description = "房租管理相关的 API")
public class RentController {
    private final TransactionsService rentService;
    @Operation(summary = "大学生租客查看房租信息")
    @PostMapping("/viewRentTenant")
    public CommonResult<RentListInfo> viewRentTenant() {
        RentListInfo rentListInfo;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            rentListInfo = rentService.viewRentTenant(userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(rentListInfo);
    }
    @Operation(summary = "房东查看所有房租信息")
    @PostMapping("/viewRentLandlord")
    public CommonResult<RentListInfo> viewRentLandlord() {
        RentListInfo rentListInfo;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            rentListInfo = rentService.viewRentLandlord(userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(rentListInfo);
    }
    @Operation(summary = "大学生租客支付房租")
    @PostMapping("/payRent")
    public CommonResult<Void> payRent(@Validated @RequestBody TransactionIdReq req) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
             rentService.payRent(req.getTransactionId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
}
