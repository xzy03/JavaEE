package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.House.req.HousePublishReq;
import cn.edu.zjut.entity.House.resp.HouseDetail;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.House.House;
import cn.edu.zjut.service.HouseService;
import cn.edu.zjut.mapper.HouseMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【house(房源信息表)】的数据库操作Service实现
* @createDate 2024-12-19 08:31:33
*/
@Service
@Slf4j
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House>
    implements HouseService{
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
                .lHouseLicensePhoto(req.getLHouseLicensePhoto())
                .lHousePhoto(req.getLHousePhoto())
                .build();
        this.save(house);
    }
    @Override
    public HouseDetail getHouseDetail(String houseId) {
        log.info("Fetching house detail for houseId: {}", houseId);
        HouseDetail houseDetail = baseMapper.getHouseDetailWithLandlord(houseId);
        if (houseDetail == null) {
            log.warn("No house detail found for houseId: {}", houseId);
        } else {
            log.info("Fetched house detail: {}", houseDetail);
        }
        return houseDetail;
    }
}




