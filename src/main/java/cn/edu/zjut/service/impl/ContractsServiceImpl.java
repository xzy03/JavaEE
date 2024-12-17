package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.Contracts.req.ContractsPublishReq;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.Contracts.Contracts;
import cn.edu.zjut.service.ContractsService;
import cn.edu.zjut.mapper.ContractsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【contracts(租房合同表)】的数据库操作Service实现
* @createDate 2024-12-17 18:47:53
*/
@Service
public class ContractsServiceImpl extends ServiceImpl<ContractsMapper, Contracts>
    implements ContractsService{
    @Override
    public void publish(ContractsPublishReq req, String landlordId) {
        Contracts contracts = Contracts.builder()
                .cHouseId(req.getCHouseId())
                .cTenantId(req.getCTenantId())
                .cStartDate(req.getCStartDate())
                .cEndDate(req.getCEndDate())
                .cRentAmount(req.getCRentAmount())
                .cDepositAmount(req.getCDepositAmount())
                .cAdditions(req.getCAdditions())
                .cStatus("未生效")
                .cLandlordId(landlordId)
                .build();
        this.save(contracts);
    }
}




