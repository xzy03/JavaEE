package cn.edu.zjut.mapper;

import cn.edu.zjut.entity.House.House;
import cn.edu.zjut.entity.House.req.QueryHouseReq;
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
* @description 针对表【tenant_profile(大学生租客表)】的数据库操作Mapper
* @createDate 2024-12-25 13:41:08
* @Entity cn.edu.zjut.entity.TenantProfile.TenantProfile
*/
public interface TenantProfileMapper extends BaseMapper<TenantProfile> {
    List<TenantProfile> getTenantList(@Param("req") QueryTenantReq req);


    //根据租户ID查询租户信息
    @Select(
            "SELECT * " +
                    "FROM tenant_profile " +
                    "WHERE tenant_id = #{tenantId}")
    TenantProfile selectByTenantId(@Param("tenantId") String tenantId);

    //更新租户的账户余额
    @Update("UPDATE tenant_profile " +
            "SET t_balance = #{tBalance} " +
            "WHERE tenant_id = #{tenantId}")
    int updateTenantBalance(String tenantId, BigDecimal tBalance);


    @Select(
            "SELECT tName " +
                    "FROM tenant_profile " +
                    "WHERE tenant_id = #{tenantId}")
    String selectTenantNameByTenantId(String tenantId);
}




