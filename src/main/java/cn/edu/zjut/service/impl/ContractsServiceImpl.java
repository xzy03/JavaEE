package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.Contracts.req.ContractsIdReq;
import cn.edu.zjut.entity.Contracts.req.ContractsPublishReq;
import cn.edu.zjut.entity.Contracts.resp.ContractsDetailResp;
import cn.edu.zjut.entity.Contracts.resp.ContractsListInfo;
import cn.edu.zjut.entity.House.House;
import cn.edu.zjut.entity.House.resp.HouseDetail;
import cn.edu.zjut.entity.HouseTenants.HouseTenants;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.entity.Transactions.Transactions;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.Contracts.Contracts;
import cn.edu.zjut.mapper.ContractsMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
* @author 86173
* @description 针对表【contracts(租房合同表)】的数据库操作Service实现
* @createDate 2024-12-17 18:47:53
*/
@Service
@Slf4j
public class ContractsServiceImpl extends ServiceImpl<ContractsMapper, Contracts>
    implements ContractsService{
    @Lazy
    @Resource
    TransactionsService transactionsService;
    @Lazy
    @Resource
    HouseTenantsService houseTenantsService;
    @Lazy
    @Resource
    HouseService houseService;
    @Lazy
    @Resource
    TenantProfileService tenantProfileService;
    @Override
    public void publish(ContractsPublishReq req, String landlordId) {
        House house = houseService.getById(req.getCHouseId());
        if(!house.getLandlordId().equals(landlordId)){
            throw new BusiException("您不是该房屋的房东，无法发布合同");
        }
        TenantProfile tenantProfile = tenantProfileService.getById(req.getCTenantId());
        if(tenantProfile == null){
            throw new BusiException("租客不存在");
        }
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
    @Override
    public ContractsDetailResp getContractsDetail(ContractsIdReq req) {
        ContractsDetailResp contractsDetailResp = baseMapper.getContractsDetail(req);
        if (contractsDetailResp == null) {
            log.warn("No house detail found for houseId: {}", req);
        } else {
            log.info("Fetched house detail: {}", contractsDetailResp);
        }
        return contractsDetailResp;
    }
    @Override
    public ContractsListInfo viewTenantContracts(String tenantId) {
        List<Contracts> contractsList = this.lambdaQuery().eq(Contracts::getCTenantId, tenantId).list();
        return ContractsListInfo.builder()
                .contractsList(contractsList)
                .build();
    }
    @Override
    public ContractsListInfo viewLandlordContracts(String landlordId) {
        List<Contracts> contractsList = this.lambdaQuery().eq(Contracts::getCLandlordId, landlordId).list();
        return ContractsListInfo.builder()
                .contractsList(contractsList)
                .build();
    }
    @Override
    @Transactional
    public void confirmContract(ContractsIdReq req) {
        Contracts contracts = this.getById(req.getContractsId());
        if(contracts == null){
            throw new BusiException("合同不存在");
        }
        contracts.setCStatus("已生效");
        HouseTenants houseTenants = HouseTenants.builder()
                .houseId(contracts.getCHouseId())
                .tenantId(contracts.getCTenantId())
                .htRentAmount(contracts.getCRentAmount())
                .htDepositAmount(contracts.getCDepositAmount())
                .build();
        houseTenantsService.save(houseTenants);
        this.updateById(contracts);
        generateTransactionRecords(contracts);
    }
    private void generateTransactionRecords(Contracts contracts) {
        // 获取合同的开始日期和结束日期
        Date startDate = contracts.getCStartDate();  // 假设 getCStartDate() 返回 java.util.Date 类型
        Date endDate = contracts.getCEndDate();      // 假设 getCEndDate() 返回 java.util.Date 类型

        // 将 java.util.Date 转换为 java.time.LocalDate
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        BigDecimal rentAmount = contracts.getCRentAmount();  // 获取租金金额
        BigDecimal depositAmount = contracts.getCDepositAmount();  // 获取押金金额
        String tenantId = contracts.getCTenantId();  // 租客 ID
        String landlordId = contracts.getCLandlordId();  // 房东 ID

        LocalDate currentMonth = startLocalDate;

        // 判断第一个月是否需要生成押金交易记录
        if (depositAmount.compareTo(BigDecimal.ZERO) > 0) {
            // 生成押金交易记录
            Transactions depositTransaction = new Transactions();
            depositTransaction.setTenantId(tenantId);
            depositTransaction.setLandlordId(landlordId);
            depositTransaction.setTTransactionType("押金支付");  // 交易类型
            depositTransaction.setTAmount(depositAmount);        // 押金金额
            depositTransaction.setTStatus("待支付");             // 交易状态
            depositTransaction.setTName("押金支付 - " + currentMonth.getMonthValue() + "/" + currentMonth.getYear());  // 交易项目
            depositTransaction.setTFees(BigDecimal.ZERO);         // 手续费（假设没有手续费）
            depositTransaction.setTPaytime(Date.from(currentMonth.atStartOfDay(ZoneId.systemDefault()).toInstant()));            // 交易时间
            // 插入押金交易记录
            transactionsService.save(depositTransaction);
        }

        // 生成每个月的租金交易记录
        while (currentMonth.isBefore(endLocalDate) || currentMonth.isEqual(endLocalDate)) {
            // 生成租金交易记录
            Transactions rentTransaction = new Transactions();
            rentTransaction.setTenantId(tenantId);
            rentTransaction.setLandlordId(landlordId);
            rentTransaction.setTTransactionType("租金支付");  // 交易类型
            rentTransaction.setTAmount(rentAmount);            // 租金金额
            rentTransaction.setTStatus("待支付");              // 交易状态
            rentTransaction.setTName("租金支付 - " + currentMonth.getMonthValue() + "/" + currentMonth.getYear());  // 交易项目
            rentTransaction.setTFees(BigDecimal.ZERO);         // 手续费（假设没有手续费）
            rentTransaction.setTPaytime(Date.from(currentMonth.atStartOfDay(ZoneId.systemDefault()).toInstant()));  // 交易时间
            // 插入租金交易记录
            transactionsService.save(rentTransaction);
            // 将当前月份推向下一个月
            currentMonth = currentMonth.plusMonths(1);
        }
    }

}




