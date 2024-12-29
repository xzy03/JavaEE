package cn.edu.zjut.controller;

import cn.edu.zjut.entity.Contracts.DTO.RentPaymentRecordDTO;
import cn.edu.zjut.entity.Contracts.resp.RentPaymentRecordResp;
import cn.edu.zjut.entity.Transactions.Req.TenantConfirmRent_Req;
import cn.edu.zjut.entity.Transactions.DTO.TenantPaymentDTO;
import cn.edu.zjut.entity.Transactions.Resp.TenantPaymentResp;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.ContractsService;
import cn.edu.zjut.service.TransactionsService;
import cn.edu.zjut.utils.UserInfoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Tag(name = "房租管理", description = "房租相关的 API")

@RestController
@RequestMapping("/api/contract-payments")
public class RentPaymentController {

    @Autowired
    private ContractsService contractsService;

    @Autowired
    private TransactionsService transactionService;


    // 房东查看合同租金
//    @Operation(summary = "房东查看合同租金", description = "房东查看合同租金")
//    @GetMapping("/landlord/contracts")
//    public CommonResult<List<Contracts>> getContractsByLandlord() {
//        try {
//            // 获取当前房东的ID，通过Token获取当前用户信息
//            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
//            String landlordId = userTokenInfoDto.getUserId();  // 获取当前房东ID
//
//            // 根据房东ID获取房东的所有合同
//            List<Contracts> contracts = contractsService.getContractsByLandlord(landlordId);
//            return CommonResult.success(contracts);
//        } catch (Exception e) {
//            return CommonResult.error(e.getMessage());
//        }
//    }

    //房东查看房租支付记录
    @Operation(summary = "房东查看房租支付记录", description = "房东查看房租支付记录")

    @PostMapping("/landlord/rent-payments")
    public CommonResult<RentPaymentRecordResp> getRentPaymentRecordsByLandlord() {
        try {
            // 获取当前房东ID，通过Token获取当前用户信息
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            String landlordId = userTokenInfoDto.getUserId();  // 获取当前房东ID
            //System.out.println(landlordId);

            // 调用服务方法，查询房东的支付记录
            List<RentPaymentRecordDTO> paymentRecords = transactionService.getRentPaymentRecordsByLandlord(landlordId);
            RentPaymentRecordResp rentPaymentRecordResp = new RentPaymentRecordResp(paymentRecords);

            // 返回支付记录列表
            return CommonResult.success(rentPaymentRecordResp);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }



    /**
     * 生成待支付的房租订单
     *
     */



//    // 学生支付租金和押金
//    @PostMapping("/tenant/{tenantId}/pay")
//    public CommonResult<String> payRentAndDeposit(@RequestBody Contracts contract) {
//        try {
//            // 获取当前学生的ID，通过Token获取当前用户信息
//            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
//            String tenantId = userTokenInfoDto.getUserId();  // 获取当前学生ID
//
//
//            // 执行支付逻辑
//            boolean success = contractsService.processPayment(contract);
//            if (success) {
//                return CommonResult.success("支付成功");
//            } else {
//                return CommonResult.error("支付失败");
//            }
//        } catch (Exception e) {
//            return CommonResult.error(e.getMessage());
//        }
//    }

    // 学生查看合同
//    @Operation(summary = "学生查看合同", description = "学生查看合同")
//    @GetMapping("/tenant/contracts")
//    public CommonResult<List<Contracts>> getTenantContracts() {
//        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
//        String tenantId = userTokenInfoDto.getUserId();  // 获取当前学生ID
//
//        try {
//            // 获取学生的所有合同
//            List<Contracts> contracts = contractsService.getValidContractByTenant(tenantId);
//            return CommonResult.success(contracts);
//        } catch (Exception e) {
//            return CommonResult.error(e.getMessage());
//        }
//    }


    /**
     * 租户查看房租支付记录
     */
    @Operation(summary = "租户查看房租支付记录", description = "租户查看房租支付记录")
    @PostMapping("/payments")
    public CommonResult<TenantPaymentResp> getRentPaymentRecords() {
        try {
            // 获取当前租户ID，通过Token获取当前用户信息
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            String tenantId = userTokenInfoDto.getUserId();  // 获取当前租户ID
            System.out.println(tenantId);
            // 获取当前时间
            Date currentDate = new Date();
            System.out.println(currentDate);

            // 查询租户的所有待支付房租记录
            List<TenantPaymentDTO> transactions = transactionService.getPendingPaymentsByTenant(tenantId, currentDate);
            TenantPaymentResp tenantPaymentResp = new TenantPaymentResp(transactions);
            // 返回符合条件的交易记录
            return CommonResult.success(tenantPaymentResp);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }


    @Operation(summary = "租客支付房租", description = "租客支付房租")
    @PostMapping("/tenant_payRent")
    public CommonResult<Void> tenantPayRent(@RequestBody TenantConfirmRent_Req tenantPayRentReq) {
        try {
            // 调用服务层方法处理支付房租的操作
            transactionService.tenantPayRent(tenantPayRentReq);
            return CommonResult.success(null);  // 返回成功
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }



}