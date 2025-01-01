package cn.edu.zjut.mapper;

import cn.edu.zjut.entity.Transactions.Transactions;
import cn.edu.zjut.entity.Transactions.req.QueryTransactionReq;
import cn.edu.zjut.entity.Transactions.resp.QueryTransactionResp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 86173
* @description 针对表【transactions(交易记录表)】的数据库操作Mapper
* @createDate 2024-12-12 23:54:37
* @Entity cn.edu.zjut.entity.Transactions.Transactions
*/
public interface TransactionsMapper extends BaseMapper<Transactions> {
    List<QueryTransactionResp> queryRentTenant(@Param("req") QueryTransactionReq req);
}




