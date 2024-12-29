package cn.edu.zjut.mapper;

import cn.edu.zjut.entity.Transactions.DTO.DepositTransactionDetail;
import cn.edu.zjut.entity.Transactions.DTO.TenantPaymentDTO;
import cn.edu.zjut.entity.Transactions.Transactions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* @author 86173
* @description 针对表【transactions(交易记录表)】的数据库操作Mapper
* @createDate 2024-12-12 23:54:37
* @Entity cn.edu.zjut.entity.Transactions.Transactions
*/
public interface TransactionsMapper extends BaseMapper<Transactions> {
    //根据合同ID查询所有的交易记录
    @Select("SELECT * " +
            "FROM transactions " +
            "WHERE landlord_id = #{landlordId}")
    List<Transactions> getTransactionsByLandlordId(@Param("landlordId") String landlordId);

    @Select("SELECT h.h_title AS houseName, " +
            "t.t_amount AS paymentAmount, " +    // 交易金额
            "t.t_status AS transactionStatus, " + // 交易状态
            "t.t_paytime AS transactionTime " +  // 交易时间
            "FROM transactions t " +
            "JOIN contracts c ON t.tenant_id = c.c_tenant_id " +
            "JOIN house h ON c.c_house_id = h.house_id " +  // 假设房屋信息在houses表中
            "WHERE t.tenant_id = #{tenantId} " +
            //"AND t.t_status = '待支付' " +  // 筛选待支付的交易记录
            "AND t.t_paytime <= #{currentDate} " +  // 确保查询出需要支付的交易记录，且支付时间小于等于当前时间
            "AND t.t_transaction_type = '租金支付' " +  // 仅筛选房租交易记录
            "ORDER BY t.t_paytime ASC")  // 按支付时间升序排列，确保按时间顺序显示
    List<TenantPaymentDTO> getPendingPaymentsByTenant(@Param("tenantId") String tenantId, @Param("currentDate") Date currentDate);


    //租户支付房租信息
    @Update("UPDATE transactions " +
            "SET t_status = #{tStatus} " +
            "WHERE transaction_id = #{transactionId}")
    void updateTransactionStatus(@Param("transactionId") String transactionId,
                                 @Param("tStatus") String tStatus);

    //利用transactionid查看房租支付记录
    @Select("SELECT * FROM transactions WHERE transaction_id = #{transactionId}")
    Transactions findTransactionById(@Param("transactionId") String transactionId);


    // 根据 tenantId 查找所有押金类型的交易记录
    @Select("SELECT t.transaction_id, t.tenant_id, t.landlord_id, t.t_amount AS deposit_amount, " +
            "t.t_transaction_type, t.t_status, t.t_paytime, h.h_title " +
            "FROM transactions t " +
            "JOIN contracts c ON t.tenant_id = c.c_tenant_id " +
            "JOIN house h ON c.c_house_id = h.house_id " +
            "WHERE t.tenant_id = #{tenantId} " +
            "AND t.t_transaction_type = '押金支付'")
    List<DepositTransactionDetail> findDepositTransactionsByTenantId(@Param("tenantId") String tenantId);


    //查看押金记录
    @Select(
            "SELECT * FROM transactions " +
                    "WHERE transaction_id=#{transactionid}" )
    Transactions getDepositTransaction(@Param("transactionid") String transactionid);

    //查看房东id
    @Select(
            "SELECT landlord_id " +
                    "FROM transactions " +
                    "WHERE transaction_id=#{transactionid}" )
    String getLandlordProfileById(@Param("transactionid") String transactionid);


    //根据房东ID查找所有租户的押金支付交易记录
    @Select("SELECT * " +
            "FROM transactions  " +
            "WHERE landlord_id = #{landlordId} " +
            "AND t_transaction_type = '押金支付'")
    List<Transactions> findDepositTransactionsByLandlordId(String landlordId);

    @Select("SELECT landlord_id FROM transactions WHERE transaction_id = #{transactionid}")
    String finglandlordId(String transactionid);


    @Select(
            "SELECT t_name " +
                    "FROM tenant_profile " +
                    "WHERE tenant_id = #{tenantId}")
    public String selectTenantNameByTenantId(String tenantId);

    @Select("SELECT t_amount "+
            "FROM transactions "+
            "WHERE transaction_id=#{transactionid}" )
    BigDecimal findBalancebyid(String transactionid);
}




