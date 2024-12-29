package cn.edu.zjut.mapper;

import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.LandlordProfile.req.QueryLandlordReq;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.entity.TenantProfile.req.QueryTenantReq;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 86173
* @description 针对表【landlord_profile(房东表)】的数据库操作Mapper
* @createDate 2024-12-18 23:19:39
* @Entity cn.edu.zjut.entity.LandlordProfile.LandlordProfile
*/
public interface LandlordProfileMapper extends BaseMapper<LandlordProfile> {
    List<LandlordProfile> getLandlordList(@Param("req") QueryLandlordReq req);

    //据房东ID查询房东信息
    @Select("SELECT *" +
            "FROM landlord_profile " +
            "WHERE landlord_id = #{landlordId}")
    LandlordProfile selectById(String landlordId);

    //更新房东信息
    @Update("UPDATE landlord_profile " +
            "SET l_balance = #{balance} " +
            "WHERE landlord_id = #{landlordid}")
    int updateById(String landlordid, BigDecimal balance);
}




