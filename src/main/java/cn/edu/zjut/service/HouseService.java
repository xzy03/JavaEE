package cn.edu.zjut.service;

import cn.edu.zjut.entity.House.House;
import cn.edu.zjut.entity.House.req.HouseInfoReq;
import cn.edu.zjut.entity.House.req.HousePublishReq;
import cn.edu.zjut.entity.House.req.QueryHouseReq;
import cn.edu.zjut.entity.House.resp.HouseDetail;
import cn.edu.zjut.entity.House.resp.HouseListInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 86173
* @description 针对表【house(房源信息表)】的数据库操作Service
* @createDate 2024-12-19 08:31:33
*/
public interface HouseService extends IService<House> {
    void publish(HousePublishReq req, String landlordId);
    HouseDetail getHouseDetail(String houseId);
    HouseListInfo getHouseList(QueryHouseReq req);
    void addHouseCard(String house_id,MultipartFile l_house_photo, MultipartFile l_house_license_photo);
    public String getLandlordIdByHouseId(String houseId);
    House changeHouseInfo(HouseInfoReq req, String landlordId);
    void rentHouse(String houseId, String tenantId);
}
