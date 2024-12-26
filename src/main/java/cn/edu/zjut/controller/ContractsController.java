package cn.edu.zjut.controller;

import cn.edu.zjut.entity.Contracts.req.ContractsIdReq;
import cn.edu.zjut.entity.Contracts.req.ContractsPublishReq;
import cn.edu.zjut.entity.Contracts.resp.ContractsDetailResp;
import cn.edu.zjut.entity.Contracts.resp.ContractsListInfo;
import cn.edu.zjut.entity.House.req.QueryHouseReq;
import cn.edu.zjut.entity.House.resp.HouseListInfo;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.ContractsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import cn.edu.zjut.utils.UserInfoUtils;
import org.springframework.web.bind.annotation.*;

/*
    * 合同控制器
    * @Author 项郑毅
    * @Date 2024/12/17
 */
@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/contacts")
@Tag(name = "合同管理", description = "合同相关的 API")
public class ContractsController {
    private final ContractsService contractsService;
    @Operation(summary="发布合同")
    @PostMapping("/publish")
    public CommonResult<Void> publish(@Validated @RequestBody ContractsPublishReq req) {
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            contractsService.publish(req, userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(null);
    }
    @Operation(summary="查看合同详细信息")
    @PostMapping("/getContractsDetail")
    public CommonResult<ContractsDetailResp> getContractsDetail(@Validated @RequestBody ContractsIdReq req) {
        ContractsDetailResp contractsDetailResp;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            contractsDetailResp = contractsService.getContractsDetail(req);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(contractsDetailResp);
    }
    @Operation(summary="大学生租户查看合同列表")
    @PostMapping("/viewTenantContracts")
    public CommonResult<ContractsListInfo> viewTenantContracts() {
        ContractsListInfo contractsListInfo;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            contractsListInfo = contractsService.viewTenantContracts(userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(contractsListInfo);
    }
    @Operation(summary="房东查看合同列表")
    @PostMapping("/viewLandlordContracts")
    public CommonResult<ContractsListInfo> viewLandlordContracts() {
        ContractsListInfo contractsListInfo;
        try {
            UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
            contractsListInfo = contractsService.viewLandlordContracts(userTokenInfoDto.getUserId());
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.success(contractsListInfo);
    }

}
