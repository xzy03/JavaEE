package cn.edu.zjut.controller;


import cn.edu.zjut.entity.Transactions.DTO.LandlordRefundDepositDTO;

import cn.edu.zjut.entity.Transactions.Req.TenantPayDepositReq;
import cn.edu.zjut.entity.Transactions.DTO.DepositTransactionDetail;
import cn.edu.zjut.entity.Transactions.Resp.DepositTransactionDetailResp;
import cn.edu.zjut.entity.Transactions.Resp.LandlordRefundDepositResp;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.service.TenantProfileService;
import cn.edu.zjut.service.TransactionsService;
import cn.edu.zjut.utils.UserInfoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "押金管理", description = "押金相关的 API")

@RestController
@RequestMapping("/api/deposit-payments")
public class DepositController {

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private LandlordProfileService landlordProfileService;

    @Autowired
    private TenantProfileService tenantProfileService;

    @Operation(summary = "租户查看押金", description = "租户查看自己的押金信息")
    @PostMapping("/viewDeposit")
    public CommonResult<DepositTransactionDetailResp> viewDeposit() {
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        //租客id
        String tenantid=userTokenInfoDto.getUserId();
        try {
            // 调用服务层方法，根据 tenantId 查找所有的押金交易信息
            List<DepositTransactionDetail> depositTransactionDetails = transactionsService.getDepositTransactionsByTenantId(tenantid);
            DepositTransactionDetailResp depositTransactionDetailResp = new DepositTransactionDetailResp(depositTransactionDetails);
            return CommonResult.success(depositTransactionDetailResp);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }

    @Operation(summary = "租户支付押金", description = "租户支付押金")
    @PostMapping("/payDeposit")
    public CommonResult<Void> payDeposit(@RequestBody TenantPayDepositReq tenantPayDepositReq) {
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        //租客id
        String tenantid=userTokenInfoDto.getUserId();
        tenantPayDepositReq.setTenantId(tenantid);
        try {
            transactionsService.payDeposit(tenantPayDepositReq);
            return CommonResult.success(null);  // 返回成功
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }

    @Operation(summary = "房东查看所有租户的押金支付情况", description = "房东查看所有租户的押金支付情况")
    @PostMapping("/landlordViewAllDeposits")
    public CommonResult<LandlordRefundDepositResp> landlordViewAllDeposits() {
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        //房东id
        String landlordid=userTokenInfoDto.getUserId();
        System.out.println(landlordid);
        try {
            // 获取房东所有租户的押金支付情况
            List<LandlordRefundDepositDTO> responseList = transactionsService.getAllDepositDetailsByLandlordId(landlordid);
            LandlordRefundDepositResp response = new LandlordRefundDepositResp(responseList);
            return CommonResult.success(response);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }

    @Operation(summary = "房东退还押金", description = "房东退还押金")
    @PostMapping("/landlordRefundDeposit")
    public CommonResult<Void> landlordRefundDeposit(@RequestBody LandlordRefundDepositDTO landlordRefundDepositReq) {
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        //房东id
        String landlordid=userTokenInfoDto.getUserId();
        landlordRefundDepositReq.setLandlordId(landlordid);
        try {
            transactionsService.landlordRefundDeposit(landlordRefundDepositReq);
            return CommonResult.success(null);  // 返回成功
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }
}
