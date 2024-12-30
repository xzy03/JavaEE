package cn.edu.zjut.service;

import cn.edu.zjut.entity.Transactions.Transactions;
import cn.edu.zjut.entity.Transactions.resp.DepositListInfo;
import cn.edu.zjut.entity.Transactions.resp.RentListInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86173
* @description 针对表【transactions(交易记录表)】的数据库操作Service
* @createDate 2024-12-12 23:54:37
*/
public interface TransactionsService extends IService<Transactions> {
    DepositListInfo viewTenantDeposit(String tenantId);
    DepositListInfo viewLandlordDeposit(String landlordId);
    void payDeposit(String transactionId);
    RentListInfo viewRentTenant(String tenantId);
    RentListInfo viewRentLandlord(String landlordId);
    void payRent(String transactionId);
}
