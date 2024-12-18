package cn.edu.zjut.controller;

import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cn.edu.zjut.utils.UserInfoUtils;
import cn.edu.zjut.entity.House.req.HousePublishReq;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/house")
@Tag(name = "房源管理", description = "房源相关的 API")
public class HouseController {
     private final HouseService houseService;
     @Operation(summary="发布房源")
     @PostMapping("/publish")
     public CommonResult<Void> publish(@Validated @RequestBody HousePublishReq req) {
         try {
             UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
             houseService.publish(req, userTokenInfoDto.getUserId());
         } catch (Exception e) {
             return CommonResult.error(e.getMessage());
         }
         return CommonResult.success(null);
     }
    // @Operation(summary="获取房源列表")
    // @PostMapping("/getHouseList")
    // public CommonResult<List<HouseInfo>> getHouseList(@Validated @RequestBody PageReq req) {
    //     try {
    //         List<HouseInfo> houseInfoList = houseService.getHouseList(req);
    //         return CommonResult.success(houseInfoList);
    //     } catch (Exception e) {
    //         return CommonResult.error(e.getMessage());
    //     }
    // }
    // @Operation(summary="获取房源详情")
    // @PostMapping("/getHouseDetail")
    // public CommonResult<HouseDetail> getHouseDetail(@Validated @RequestBody HouseDetailReq req) {
    //     try {
    //         HouseDetail houseDetail = houseService.getHouseDetail(req);
    //         return CommonResult.success(houseDetail);
    //     } catch (Exception e) {
    //         return CommonResult.error(e.getMessage());
    //     }
    // }
    // @Operation(summary="修改房源信息")
    // @PostMapping("/changeHouseInfo")
    // public CommonResult<Void> changeHouseInfo(@Validated @RequestBody HouseInfoReq req) {
    //     try {
    //         houseService.changeHouseInfo(req);
    //     } catch (Exception e) {
    //         return CommonResult.error(e.getMessage());
    //     }
    //     return CommonResult.success(null);
    // }
    // @Operation(summary="删除房源")
    // @PostMapping("/deleteHouse")
    // public CommonResult<Void> deleteHouse(@Validated @RequestBody HouseDeleteReq req) {
    //     try {
    //         houseService.deleteHouse(req);
    //     } catch (Exception e) {
    //         return CommonResult.error(e.getMessage());
    //     }
    //
}
