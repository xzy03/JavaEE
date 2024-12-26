package cn.edu.zjut.service;

import cn.edu.zjut.entity.Contracts.Contracts;
import cn.edu.zjut.entity.Contracts.req.ContractsIdReq;
import cn.edu.zjut.entity.Contracts.req.ContractsPublishReq;
import cn.edu.zjut.entity.Contracts.resp.ContractsDetailResp;
import cn.edu.zjut.entity.Contracts.resp.ContractsListInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86173
* @description 针对表【contracts(租房合同表)】的数据库操作Service
* @createDate 2024-12-17 18:47:54
*/
public interface ContractsService extends IService<Contracts> {
    void publish(ContractsPublishReq req, String landlordId);
    ContractsDetailResp getContractsDetail(ContractsIdReq req);
    ContractsListInfo viewTenantContracts(String tenantId);
    ContractsListInfo viewLandlordContracts(String landlordId);
}
