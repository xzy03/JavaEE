package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.Contracts.Contracts;
import cn.edu.zjut.entity.House.req.HouseInfoReq;
import cn.edu.zjut.entity.House.req.HousePublishReq;
import cn.edu.zjut.entity.House.req.QueryHouseReq;
import cn.edu.zjut.entity.House.resp.HouseDetail;
import cn.edu.zjut.entity.House.resp.HouseListInfo;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.service.ContractsService;
import cn.edu.zjut.service.TenantProfileService;
import cn.edu.zjut.utils.UploadGiteeImgBed;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.House.House;
import cn.edu.zjut.service.HouseService;
import cn.edu.zjut.mapper.HouseMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author 86173
* @description 针对表【house(房源信息表)】的数据库操作Service实现
* @createDate 2024-12-19 08:31:33
*/
@Service
@Slf4j
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House>
    implements HouseService{
    @Lazy
    @Resource
    HouseService houseService;
    @Lazy
    @Resource
    ContractsService contractsService;
    @Lazy
    @Resource
    TenantProfileService tenantProfileService;
    @Override
    public void publish(HousePublishReq req, String landlordId){
        House house = House.builder()
                .landlordId(landlordId)
                .communityId(req.getCommunityId())
                .hTitle(req.getHTitle())
                .hLocation(req.getHLocation())
                .hRent(req.getHRent())
                .hArea(req.getHArea())
                .hRooms(req.getHRooms())
                .hAvailableFrom(req.getHAvailableFrom())
                .hDirection(req.getHDirection())
                .hFloor(req.getHFloor())
                .hTotalFloors(req.getHTotalFloors())
                .hFacilities(req.getHFacilities())
                .hPetFriendly(req.getHPetFriendly())
                .hTenantrequired(req.getHTenantrequired())
                .hTotalTenants(req.getHTotalTenants())
                .hRemainingVacancies(req.getHRemainingVacancies())
                .build();
        this.save(house);
    }
    @Override
    public HouseDetail getHouseDetail(String houseId) {
        HouseDetail houseDetail = baseMapper.getHouseDetailWithLandlord(houseId);
        if (houseDetail == null) {
            log.warn("No house detail found for houseId: {}", houseId);
        } else {
            log.info("Fetched house detail: {}", houseDetail);
        }
        return houseDetail;
    }
    @Override
    public HouseListInfo getHouseList(QueryHouseReq req) {
        List<House> houseList = baseMapper.getHouseList(req);
        HouseListInfo houseListInfo = HouseListInfo.builder()
                .houseList(houseList)
                .build();
        return houseListInfo;
    }
    @Override
    public void addHouseCard(String house_id, MultipartFile l_house_photo, MultipartFile l_house_license_photo){
        House house = houseService.getById(house_id);
        if (house == null) {
            throw new BusiException("房源不存在");
        }
        if(house.getLHouseLicenseState()!=null){
            if(house.getLHouseLicenseState().equals("已审核")){
                throw new BusiException("已审核通过，无需再次上传");
            }
            else if(house.getLHouseLicenseState().equals("等待审核")){
                throw new BusiException("正在审核中，请勿重复上传");
            }
        }

        String originalFilename = l_house_photo.getOriginalFilename();
        String originalFilename1 = l_house_license_photo.getOriginalFilename();
        if (originalFilename == null || originalFilename1 == null) {
            throw new BusiException("文件名为空");
        }
        String targetURL = UploadGiteeImgBed.createUploadFileUrl(originalFilename);
        String targetURL1 = UploadGiteeImgBed.createUploadFileUrl(originalFilename1);
        log.info("目标url：" + targetURL);
        log.info("目标url：" + targetURL1);
        try {
            Map<String, Object> uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(l_house_photo.getBytes());
            String JSONResult = HttpUtil.post(targetURL, uploadBodyMap);
            JSONObject jsonObj = JSONUtil.parseObj(JSONResult);
            if (jsonObj == null || jsonObj.getObj("commit") == null) {
                throw new BusiException("上传失败");
            }
            JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));
            log.info("响应data为：" + content.getObj("download_url"));
            house.setLHousePhoto(content.getStr("download_url"));
            uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(l_house_license_photo.getBytes());
            JSONResult = HttpUtil.post(targetURL1, uploadBodyMap);
            jsonObj = JSONUtil.parseObj(JSONResult);
            if (jsonObj == null || jsonObj.getObj("commit") == null) {
                throw new BusiException("上传失败");
            }
            content = JSONUtil.parseObj(jsonObj.getObj("content"));
            log.info("响应data为：" + content.getObj("download_url"));
            house.setLHouseLicensePhoto(content.getStr("download_url"));
            house.setLHouseLicenseState("等待审核");
            houseService.updateById(house);
        } catch (IOException e) {
            log.error("文件读取失败", e);
            throw new BusiException("文件读取失败，请稍后再试");
        }
    }

    @Override
    public String getLandlordIdByHouseId(String houseId) {
        // 查询 house 表，返回 landlordId
        House house = baseMapper.selectById(houseId);
        if (house == null) {
            throw new BusiException("房源不存在");
        }
        return house.getLandlordId();
    }

    @Override
    public String getHTitleByHouseId(String houseId) {
        // 查询 house 表，返回 landlordId
        House house = baseMapper.selectById(houseId);
        if (house == null) {
            throw new BusiException("房源不存在");
        }
        return house.getHTitle();
    }

    @Override
    public House changeHouseInfo(HouseInfoReq req, String landlordId) {
        House house = houseService.getById(req.getHouseId());
        if (house == null) {
            throw new BusiException("房源不存在");
        }
        if (!house.getLandlordId().equals(landlordId)) {
            throw new BusiException("您不是该房屋的房东，无法修改房源信息");
        }
        house.setHTitle(req.getHTitle());
        house.setHLocation(req.getHLocation());
        house.setHRent(req.getHRent());
        house.setHArea(req.getHArea());
        house.setHRooms(req.getHRooms());
        house.setHAvailableFrom(req.getHAvailableFrom());
        house.setHDirection(req.getHDirection());
        house.setHFloor(req.getHFloor());
        house.setHTotalFloors(req.getHTotalFloors());
        house.setHFacilities(req.getHFacilities());
        house.setHPetFriendly(req.getHPetFriendly());
        house.setHTenantrequired(req.getHTenantrequired());
        house.setHTotalTenants(req.getHTotalTenants());
        house.setHRemainingVacancies(req.getHRemainingVacancies());
        this.updateById(house);
        return house;
    }
    @Override
    public void rentHouse(String houseId, String tenantId) {
        TenantProfile tenantProfile = tenantProfileService.getById(tenantId);
        if(tenantProfile.getTIdentityStatus()==null){
            throw new BusiException("请先进行实名认证");
        }
        if(tenantProfile.getTStatus()==null){
            throw new BusiException("请先进行学生认证");
        }
        if(tenantProfile.getTIdentityStatus().equals("等待审核")||tenantProfile.getTStatus().equals("等待审核")){
            throw new BusiException("请等待审核通过后再进行租房");
        }
        House house = houseService.getById(houseId);
        if (house == null) {
            throw new BusiException("房源不存在");
        }
        if (house.getHRemainingVacancies() <= 0) {
            throw new BusiException("该房屋已无空余房间");
        }
        Contracts contracts = Contracts.builder()
                .cHouseId(houseId)
                .cTenantId(tenantId)
                .cLandlordId(house.getLandlordId())
                .cStatus("未生效")
                .build();
        contractsService.save(contracts);
    }
}




