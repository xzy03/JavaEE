package cn.edu.zjut.service;

import cn.edu.zjut.entity.House.House;
import cn.edu.zjut.entity.House.req.HousePublishReq;
import cn.edu.zjut.entity.House.resp.HouseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86173
* @description 针对表【house(房源信息表)】的数据库操作Service
* @createDate 2024-12-19 08:31:33
*/
public interface HouseService extends IService<House> {
    void publish(HousePublishReq req, String landlordId);
    HouseDetail getHouseDetail(String houseId);
}
