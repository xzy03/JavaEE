package cn.edu.zjut.mapper;

import cn.edu.zjut.entity.House.House;
import cn.edu.zjut.entity.House.req.QueryHouseReq;
import cn.edu.zjut.entity.House.resp.HouseDetail;
import cn.edu.zjut.entity.House.resp.HouseListInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 86173
* @description 针对表【house(房源信息表)】的数据库操作Mapper
* @createDate 2024-12-19 08:31:33
* @Entity cn.edu.zjut.entity.House.House
*/
public interface HouseMapper extends BaseMapper<House> {
     HouseDetail getHouseDetailWithLandlord(@Param("houseId") String houseId);
     List<House> getHouseList(@Param("req") QueryHouseReq req);
}




