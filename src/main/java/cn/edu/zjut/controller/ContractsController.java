package cn.edu.zjut.controller;

import cn.edu.zjut.entity.Contracts.req.ContractsPublishReq;
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

}
