package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.Transactions.Transactions;
import cn.edu.zjut.service.TransactionsService;
import cn.edu.zjut.mapper.TransactionsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【transactions(交易记录表)】的数据库操作Service实现
* @createDate 2024-12-12 23:54:37
*/
@Service
public class TransactionsServiceImpl extends ServiceImpl<TransactionsMapper, Transactions>
    implements TransactionsService{

}




