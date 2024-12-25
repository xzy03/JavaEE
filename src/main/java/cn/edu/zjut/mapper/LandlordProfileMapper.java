package cn.edu.zjut.mapper;

import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.LandlordProfile.req.QueryLandlordReq;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.entity.TenantProfile.req.QueryTenantReq;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 86173
* @description 针对表【landlord_profile(房东表)】的数据库操作Mapper
* @createDate 2024-12-18 23:19:39
* @Entity cn.edu.zjut.entity.LandlordProfile.LandlordProfile
*/
public interface LandlordProfileMapper extends BaseMapper<LandlordProfile> {
    List<LandlordProfile> getLandlordList(@Param("req") QueryLandlordReq req);
}




