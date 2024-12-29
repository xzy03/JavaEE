package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.Contracts.DTO.RentPaymentRecordDTO;
import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.entity.Transactions.DTO.LandlordRefundDepositDTO;
import cn.edu.zjut.entity.Transactions.Req.TenantConfirmRent_Req;
import cn.edu.zjut.entity.Transactions.Req.TenantPayDepositReq;
import cn.edu.zjut.entity.Transactions.DTO.DepositTransactionDetail;
import cn.edu.zjut.entity.Transactions.DTO.TenantPaymentDTO;
import cn.edu.zjut.entity.Transactions.Transactions;
import cn.edu.zjut.mapper.ContractsMapper;
import cn.edu.zjut.mapper.TenantProfileMapper;
import cn.edu.zjut.mapper.TransactionsMapper;
import cn.edu.zjut.service.ContractsService;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.service.TenantProfileService;
import cn.edu.zjut.service.TransactionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Autowired
    private TransactionsMapper transactionMapper;

    @Autowired
    private TenantProfileMapper tenantProfileMapper;

    @Autowired
    private ContractsService contractsService;

    @Autowired
    private ContractsMapper contractsMapper;




    @Autowired
    private TenantProfileService tenantProfileService;

    @Autowired
    private LandlordProfileService landlordProfileService;






    //根据房东ID查询所有租户的支付记录
    public List<RentPaymentRecordDTO> getRentPaymentRecordsByLandlord(String landlordId) {
        // 通过房东ID直接查询所有交易记录
        List<Transactions> transactions = transactionMapper.getTransactionsByLandlordId(landlordId);

        // 如果未找到交易记录，抛出异常
        if (transactions == null || transactions.isEmpty()) {
            throw new RuntimeException("未找到相关支付记录");
        }

        // 用于存储支付记录DTO
        List<RentPaymentRecordDTO> paymentRecordDTOList = new ArrayList<>();

        // 遍历所有交易记录，将每个交易记录封装成 RentPaymentRecordDTO
        for (Transactions transaction : transactions) {
            RentPaymentRecordDTO recordDTO = new RentPaymentRecordDTO();

            // 获取租户的名字，直接通过租户ID查询
            String tenantName = tenantProfileMapper.selectTenantNameByTenantId(transaction.getTenantId());
            recordDTO.setTenantName(tenantName);

            // 设置支付金额、支付时间和交易状态
            recordDTO.setPaymentAmount(transaction.getTAmount());
            recordDTO.setPaymentDate(transaction.getTPaytime());
            recordDTO.setTransactionStatus(transaction.getTStatus());
            recordDTO.setHouseName(contractsMapper.findhousename(transaction.getTenantId()));


            // 将封装好的记录添加到返回列表中
            paymentRecordDTOList.add(recordDTO);
        }

        return paymentRecordDTOList;
    }


    /**
     * 获取租户的待支付房租记录
     */
    public List<TenantPaymentDTO> getPendingPaymentsByTenant(String tenantId, Date currentDate) {
        // 查询租户的所有待支付房租记录，支付时间小于当前时间且状态为“待支付”
        return transactionMapper.getPendingPaymentsByTenant(tenantId, currentDate);
    }

    //租户支付房租
    @Override
    @Transactional  // 确保整个操作的原子性
    public void tenantPayRent(TenantConfirmRent_Req tenantConfirmRentReq) {
        String transactionId = tenantConfirmRentReq.getTransactionId();
        String tenantId = tenantConfirmRentReq.getTenantId();
        String landlordId = transactionMapper.finglandlordId(transactionId);
        String transactionStatus = tenantConfirmRentReq.getTStatus();  // 比如 "confirmed" 或 "paid" 等状态

        // 1. 获取租客信息
        TenantProfile tenantProfile = tenantProfileService.getTenantProfileById(tenantId);
        if (tenantProfile == null) {
            throw new RuntimeException("租客信息不存在");
        }

        // 2. 获取房东信息
        LandlordProfile landlordProfile = landlordProfileService.getLandlordProfileById(landlordId);
        if (landlordProfile == null) {
            throw new RuntimeException("房东信息不存在");
        }

        // 3. 获取交易记录（如支付金额等）
        Transactions transaction = transactionMapper.findTransactionById(transactionId);
        if (transaction == null) {
            throw new RuntimeException("交易记录不存在");
        }

        BigDecimal rentAmount = transaction.getTAmount(); // 获取房租金额
        BigDecimal tenantBalance = tenantProfile.getTBalance(); // 租客的余额

        // 4. 校验租客余额是否足够支付
        if (tenantBalance.compareTo(rentAmount) < 0) {
            throw new RuntimeException("租客余额不足，无法支付房租");
        }

        // 5. 扣除租客余额
        tenantProfile.setTBalance(tenantBalance.subtract(rentAmount));
        boolean isTenantBalanceUpdated = tenantProfileService.updateTenantProfileBalance(tenantProfile.getTenantId(),tenantProfile.getTBalance());
        if (!isTenantBalanceUpdated) {
            throw new RuntimeException("更新租客余额失败");
        }

        // 6. 增加房东余额
        BigDecimal landlordBalance = landlordProfile.getLBalance(); // 房东的余额
        landlordProfile.setLBalance(landlordBalance.add(rentAmount));
        boolean isLandlordBalanceUpdated = landlordProfileService.updateLandlordProfile(landlordProfile);
        if (!isLandlordBalanceUpdated) {
            throw new RuntimeException("更新房东余额失败");
        }

        // 7. 更新交易状态为已支付
        transactionMapper.updateTransactionStatus(transactionId, "已支付"); // 假设交易状态为 "paid"
    }



    // 根据 tenantId 查找所有押金交易记录
    public List<DepositTransactionDetail> getDepositTransactionsByTenantId(String tenantId) {
        return transactionMapper.findDepositTransactionsByTenantId(tenantId);
    }



    //租户支付押金
    @Transactional // 保证整个操作的原子性
    public void payDeposit(TenantPayDepositReq tenantPayDepositReq) {
        // 1. 查询交易记录，确保是押金支付
        Transactions transaction = transactionMapper.getDepositTransaction(tenantPayDepositReq.getTransactionId());

        if (transaction == null) {
            throw new RuntimeException("没有找到对应的押金交易记录");
        }

        // 2. 校验租户余额是否足够
        TenantProfile tenantProfile = tenantProfileService.getTenantProfileById(tenantPayDepositReq.getTenantId());
        if (tenantProfile == null) {
            throw new RuntimeException("租户信息不存在");
        }

        BigDecimal tenantBalance = tenantProfileMapper.getTenantBalance(tenantPayDepositReq.getTenantId());
        BigDecimal depositAmount = transactionMapper.findBalancebyid(tenantPayDepositReq.getTenantId());
        System.out.println(tenantBalance);
        System.out.println(depositAmount);

        if (tenantBalance.compareTo(depositAmount) < 0) {
            throw new RuntimeException("租户余额不足，无法支付押金");
        }
        System.out.println();

        // 3. 扣除租户余额
        tenantProfile.setTBalance(tenantBalance.subtract(depositAmount));
        boolean isTenantBalanceUpdated = tenantProfileService.updateTenantProfileBalance(tenantProfile.getTenantId(),tenantProfile.getTBalance());
        if (!isTenantBalanceUpdated) {
            throw new RuntimeException("更新租户余额失败");
        }

        // 4. 增加房东余额
        String landlordid=transactionMapper.getLandlordProfileById(tenantPayDepositReq.getTransactionId());
        LandlordProfile landlordProfile = landlordProfileService.getLandlordProfileById(landlordid);
        if (landlordProfile == null) {
            throw new RuntimeException("房东信息不存在");
        }

        BigDecimal landlordBalance = landlordProfile.getLBalance();
        landlordProfile.setLBalance(landlordBalance.add(depositAmount));
        boolean isLandlordBalanceUpdated = landlordProfileService.updateLandlordProfile(landlordProfile);
        if (!isLandlordBalanceUpdated) {
            throw new RuntimeException("更新房东余额失败");
        }

        // 5. 更新交易记录状态为已支付
        transaction.setTStatus("已支付");  // 修改交易状态为已支付
        try{
            transactionMapper.updateTransactionStatus(transaction.getTransactionId(), "已支付");
        }
        catch (Exception e){
            throw new RuntimeException("更新交易状态失败");
        }

    }



    public List<LandlordRefundDepositDTO> getAllDepositDetailsByLandlordId(String landlordId) {
        // 1. 根据房东ID查找所有相关的租户押金交易信息
        List<Transactions> depositTransactions = transactionMapper.findDepositTransactionsByLandlordId(landlordId);

        if (depositTransactions == null || depositTransactions.isEmpty()) {
            throw new RuntimeException("没有找到相关租户的押金支付信息");
        }

        // 2. 创建响应列表，将每个交易记录转换为 LandlordRefundDepositReq
        List<LandlordRefundDepositDTO> responseList = new ArrayList<>();
        for (Transactions depositTransaction : depositTransactions) {
            String tenantId = depositTransaction.getTenantId();
            TenantProfile tenantProfile = tenantProfileService.getTenantProfileById(tenantId);

            if (tenantProfile == null) {
                throw new RuntimeException("未找到租户信息");
            }



            // 获取支付状态
            String paymentStatus = depositTransaction.getTStatus();

            // 创建并设置返回的 LandlordRefundDepositReq 对象
            LandlordRefundDepositDTO response = new LandlordRefundDepositDTO();
            response.setTenantId(tenantId);
            String tenantname=transactionMapper.selectTenantNameByTenantId(tenantId);
            response.setTenantName(tenantname);  // 租户姓名
            response.setDepositAmount(depositTransaction.getTAmount());  // 押金金额
            response.setPaymentStatus(paymentStatus);  // 设置支付状态

            responseList.add(response);
        }

        return responseList;
    }


    //房东退还押金
    @Transactional // 确保操作的原子性
    public void landlordRefundDeposit(LandlordRefundDepositDTO landlordRefundDepositReq) {
        // 根据 transactionId 查找交易记录
        Transactions transaction = transactionMapper.selectById(landlordRefundDepositReq.getTransactionId());

        if (transaction == null) {
            throw new RuntimeException("找不到相关的押金交易记录！");
        }

        // 校验押金状态（只能退款已支付的押金）
        if (!"已支付".equals(transaction.getTStatus())) {
            throw new RuntimeException("押金尚未支付，无法进行退还！");
        }

        // 获取租户信息
        TenantProfile tenantProfile = tenantProfileService.getTenantProfileById(transaction.getTenantId());
        if (tenantProfile == null) {
            throw new RuntimeException("租户信息不存在！");
        }

        // 获取房东信息
        LandlordProfile landlordProfile = landlordProfileService.getLandlordProfileById(landlordRefundDepositReq.getLandlordId());
        if (landlordProfile == null) {
            throw new RuntimeException("房东信息不存在！");
        }

        // 校验退还金额是否正确
        BigDecimal refundAmount = transaction.getTAmount();

        // 更新租户的余额
        tenantProfile.setTBalance(tenantProfile.getTBalance().add(refundAmount));
        boolean isTenantBalanceUpdated = tenantProfileService.updateTenantProfileBalance(tenantProfile.getTenantId(),tenantProfile.getTBalance());
        if (!isTenantBalanceUpdated) {
            throw new RuntimeException("更新租户余额失败！");
        }

        // 更新房东的余额
        if(landlordProfile.getLBalance().compareTo(refundAmount) < 0){
            throw new RuntimeException("房东余额不足，无法退还押金！");
        }
        else{
            landlordProfile.setLBalance(landlordProfile.getLBalance().subtract(refundAmount));
            boolean isLandlordBalanceUpdated = landlordProfileService.updateLandlordProfile(landlordProfile);
            if (!isLandlordBalanceUpdated) {
                throw new RuntimeException("更新房东余额失败！");
            }
        }


        // 更新交易记录的状态为“已退还”
        transaction.setTStatus("已退还");
        try {
            transactionMapper.updateById(transaction);
        }
        catch (Exception e){
            throw new RuntimeException("更新交易记录失败！");
        }

        // 9. 退还成功
        // 如果所有操作都成功，事务将自动提交，否者回滚
    }

}




