package cn.edu.zjut.mapper;

import cn.edu.zjut.entity.Contracts.Contracts;
import cn.edu.zjut.entity.Contracts.req.ContractsIdReq;
import cn.edu.zjut.entity.Contracts.resp.ContractsDetailResp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 86173
* @description 针对表【contracts(租房合同表)】的数据库操作Mapper
* @createDate 2024-12-17 18:47:54
* @Entity cn.edu.zjut.entity.Contracts.Contracts
*/
public interface ContractsMapper extends BaseMapper<Contracts> {
    ContractsDetailResp getContractsDetail(@Param("req") ContractsIdReq req);
}




