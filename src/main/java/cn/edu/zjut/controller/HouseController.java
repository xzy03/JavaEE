package cn.edu.zjut.controller;

import cn.edu.zjut.entity.House.House;
import cn.edu.zjut.entity.House.req.HouseIdReq;
import cn.edu.zjut.entity.House.req.HouseInfoReq;
import cn.edu.zjut.entity.House.req.QueryHouseReq;
import cn.edu.zjut.entity.House.resp.HouseDetail;
import cn.edu.zjut.entity.House.resp.HouseListInfo;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     @Operation(summary="获取房源列表")
     @PostMapping("/getHouseList")
     public CommonResult<HouseListInfo> getHouseList(@Validated @RequestBody QueryHouseReq req) {
         HouseListInfo houseInfoList;
         try {
             houseInfoList = houseService.getHouseList(req);
         } catch (Exception e) {
             return CommonResult.error(e.getMessage());
         }
         return CommonResult.success(houseInfoList);
     }
     @Operation(summary="获取房源详情")
     @PostMapping("/getHouseDetail")
     public CommonResult<HouseDetail> getHouseDetail(@Validated @RequestBody HouseIdReq req) {
         try {
             log.info("Fetching house detail for houseId: {}", req.getHouseId());
             HouseDetail houseDetail = houseService.getHouseDetail(req.getHouseId());
             return CommonResult.success(houseDetail);
         } catch (Exception e) {
             return CommonResult.error(e.getMessage());
         }
     }
     @Operation(summary="增加房产证信息")
     @PostMapping("/addHouseCard")
     public CommonResult<Void> addHouseCard(
             @RequestParam("house_id") String house_id,
             @RequestParam("l_house_photo") MultipartFile l_house_photo,
             @RequestParam("l_house_license_photo") MultipartFile l_house_license_photo) {
         try {
             houseService.addHouseCard(house_id,l_house_photo,l_house_license_photo);
         } catch (Exception e) {
             return CommonResult.error(e.getMessage());
         }
         return CommonResult.success(null);
     }
     @Operation(summary="修改房源信息")
     @PostMapping("/changeHouseInfo")
     public CommonResult<House> changeHouseInfo(@Validated @RequestBody HouseInfoReq req) {
         System.out.println("进入/house/changeHouseInfo");
         House house;
         try {
             UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
             house = houseService.changeHouseInfo(req, userTokenInfoDto.getUserId());
         } catch (Exception e) {
             return CommonResult.error(e.getMessage());
         }
         return CommonResult.success(house);
     }
     @Operation(summary="大学生租客想要租房")
     @PostMapping("/rentHouse")
     public CommonResult<Void> rentHouse(@Validated @RequestBody HouseIdReq req) {
         try {
             UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
             houseService.rentHouse(req.getHouseId(), userTokenInfoDto.getUserId());
         } catch (Exception e) {
             return CommonResult.error(e.getMessage());
         }
         return CommonResult.success(null);
     }

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
