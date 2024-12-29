package cn.edu.zjut.service;

import cn.edu.zjut.entity.Contracts.DTO.RentPaymentRecordDTO;
import cn.edu.zjut.entity.Transactions.DTO.LandlordRefundDepositDTO;
import cn.edu.zjut.entity.Transactions.Req.TenantConfirmRent_Req;
import cn.edu.zjut.entity.Transactions.Req.TenantPayDepositReq;
import cn.edu.zjut.entity.Transactions.DTO.DepositTransactionDetail;
import cn.edu.zjut.entity.Transactions.DTO.TenantPaymentDTO;

import cn.edu.zjut.entity.Transactions.Transactions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
* @author 86173
* @description 针对表【transactions(交易记录表)】的数据库操作Service
* @createDate 2024-12-12 23:54:37
*/
public interface TransactionsService extends IService<Transactions> {
    List<RentPaymentRecordDTO> getRentPaymentRecordsByLandlord(String landlordId) ;

    //获取租户的待支付房租记录
    public List<TenantPaymentDTO> getPendingPaymentsByTenant(String tenantId, Date currentDate);

    public void tenantPayRent(TenantConfirmRent_Req tenantConfirmRentReq);


    //租客查看押金
    public List<DepositTransactionDetail> getDepositTransactionsByTenantId(String tenantId);

    //租户支付租金
    public void payDeposit(TenantPayDepositReq tenantPayDepositReq);

    //房东查看押金支付记录
    public List<LandlordRefundDepositDTO> getAllDepositDetailsByLandlordId(String landlordId);

    //房东退还租金
    public void landlordRefundDeposit(LandlordRefundDepositDTO landlordRefundDepositReq);
}
