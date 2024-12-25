package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.House.req.HousePublishReq;
import cn.edu.zjut.entity.House.req.QueryHouseReq;
import cn.edu.zjut.entity.House.resp.HouseDetail;
import cn.edu.zjut.entity.House.resp.HouseListInfo;
import cn.edu.zjut.exception.apiException.BusiException;
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

    public String getLandlordIdByHouseId(String houseId) {
        // 查询 house 表，返回 landlordId
        House house = baseMapper.selectById(houseId);
        if (house == null) {
            throw new BusiException("房源不存在");
        }
        return house.getLandlordId();
    }

}




