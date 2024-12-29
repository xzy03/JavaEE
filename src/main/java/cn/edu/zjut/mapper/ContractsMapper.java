package cn.edu.zjut.mapper;

import cn.edu.zjut.entity.Contracts.Contracts;
import cn.edu.zjut.entity.Contracts.req.ContractsIdReq;
import cn.edu.zjut.entity.Contracts.resp.ContractsDetailResp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
* @author 86173
* @description 针对表【contracts(租房合同表)】的数据库操作Mapper
* @createDate 2024-12-17 18:47:54
* @Entity cn.edu.zjut.entity.Contracts.Contracts
*/
public interface ContractsMapper extends BaseMapper<Contracts> {
    ContractsDetailResp getContractsDetail(@Param("req") ContractsIdReq req);

    @Select("SELECT * FROM contracts WHERE c_landlord_id = #{landlordId}")
    List<Contracts> getContractsByLandlordId(String landlordId);

    @Select("SELECT * FROM contracts " +
            "WHERE c_tenant_id = #{tenantId} " +  // 使用数据库列名
            "AND c_status = '有效' " +            // 只查询状态为“有效”的合同
            "AND c_start_date <= #{currentDate} " +  // 合同开始日期小于等于当前时间
            "AND c_end_date >= #{currentDate}")   // 合同结束日期大于等于当前时间

    List<Contracts> getValidContractsByTenant(@Param("tenantId") String tenantId,
                                              @Param("currentDate") Date currentDate);


    @Select("SELECT tp.t_name " +
            "FROM contracts c " +
            "JOIN tenant_profile tp ON c.c_tenant_id = tp.tenant_id " +
            "WHERE c.c_tenant_id = #{tenantId}")
    String findhousename(String tenantid);
}




