package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.entity.Transactions.req.QueryTransactionReq;
import cn.edu.zjut.entity.Transactions.resp.DepositListInfo;
import cn.edu.zjut.entity.Transactions.resp.QueryTransactionResp;
import cn.edu.zjut.entity.Transactions.resp.RentListInfo;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.service.TenantProfileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.Transactions.Transactions;
import cn.edu.zjut.service.TransactionsService;
import cn.edu.zjut.mapper.TransactionsMapper;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
* @author 86173
* @description 针对表【transactions(交易记录表)】的数据库操作Service实现
* @createDate 2024-12-12 23:54:37
*/
@Service
public class TransactionsServiceImpl extends ServiceImpl<TransactionsMapper, Transactions>
    implements TransactionsService{
    @Lazy
    @Resource
    TransactionsService transactionsService;
    @Lazy
    @Resource
    TenantProfileService tenantProfileService;
    @Lazy
    @Resource
    LandlordProfileService landlordProfileService;
    @Override
    public DepositListInfo viewTenantDeposit(String tenantId) {
        List<Transactions> depositList = this.lambdaQuery()
                .eq(Transactions::getTenantId, tenantId)
                .eq(Transactions::getTTransactionType, "押金支付")
                .list();
        return DepositListInfo.builder().depositList(depositList).build();
    }
    @Override
    public DepositListInfo viewLandlordDeposit(String landlordId) {
        List<Transactions> depositList = this.lambdaQuery()
                .eq(Transactions::getLandlordId, landlordId)
                .eq(Transactions::getTTransactionType, "押金支付")
                .list();
        return DepositListInfo.builder().depositList(depositList).build();
    }
    @Override
    @Transactional
    public void payDeposit(String transactionId) {
        Transactions transaction = transactionsService.getById(transactionId);
        if (transaction == null) {
            throw new RuntimeException("交易记录不存在");
        }
        TenantProfile tenantProfile = tenantProfileService.getById(transaction.getTenantId());
        LandlordProfile landlordProfile = landlordProfileService.getById(transaction.getLandlordId());
        if (!transaction.getTStatus().equals("待支付")) {
            throw new RuntimeException("交易状态不正确");
        }
        if(tenantProfile.getTBalance().compareTo(transaction.getTAmount()) < 0) {
            throw new RuntimeException("余额不足");
        }
        transaction.setTStatus("已支付");
        tenantProfile.setTBalance(tenantProfile.getTBalance().subtract(transaction.getTAmount()));
        landlordProfile.setLBalance(landlordProfile.getLBalance().add(transaction.getTAmount()));
        tenantProfileService.updateById(tenantProfile);
        landlordProfileService.updateById(landlordProfile);
        this.updateById(transaction);
    }
    @Override
    public RentListInfo viewRentTenant(String tenantId) {
        List<Transactions> rentList = this.lambdaQuery()
                .eq(Transactions::getTenantId, tenantId)
                .eq(Transactions::getTTransactionType, "租金支付")
                .list();
        return RentListInfo.builder().rentList(rentList).build();
    }
    @Override
    public RentListInfo viewRentLandlord(String landlordId) {
        List<Transactions> rentList = this.lambdaQuery()
                .eq(Transactions::getLandlordId, landlordId)
                .eq(Transactions::getTTransactionType, "租金支付")
                .list();
        return RentListInfo.builder().rentList(rentList).build();
    }
    @Override
    @Transactional
    public void payRent(String transactionId) {
        Transactions transaction = transactionsService.getById(transactionId);
        if (transaction == null) {
            throw new RuntimeException("交易记录不存在");
        }
        TenantProfile tenantProfile = tenantProfileService.getById(transaction.getTenantId());
        LandlordProfile landlordProfile = landlordProfileService.getById(transaction.getLandlordId());
        if (!transaction.getTStatus().equals("待支付")) {
            throw new RuntimeException("交易状态不正确");
        }
        if (tenantProfile.getTBalance().compareTo(transaction.getTAmount()) < 0) {
            throw new RuntimeException("余额不足");
        }
        transaction.setTStatus("已支付");
        // 获取今天的日期
        LocalDate today = LocalDate.now();
        // 转换为当天0点的LocalDateTime
        LocalDateTime midnightLocalDateTime = today.atStartOfDay();
        // 将 LocalDateTime 转换为 Date
        Date midnight = Date.from(midnightLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        // 设置交易时间为当天0点
        transaction.setTPaytime(midnight);
        tenantProfile.setTBalance(tenantProfile.getTBalance().subtract(transaction.getTAmount()));
        landlordProfile.setLBalance(landlordProfile.getLBalance().add(transaction.getTAmount()));
        tenantProfileService.updateById(tenantProfile);
        landlordProfileService.updateById(landlordProfile);
        this.updateById(transaction);
    }
    @Override
    public List<QueryTransactionResp> queryRentTenant(QueryTransactionReq req) {
        List<QueryTransactionResp> rentList = baseMapper.queryRentTenant(req);
        return rentList;
    }
}




