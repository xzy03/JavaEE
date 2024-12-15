package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.Contracts.Contracts;
import cn.edu.zjut.service.ContractsService;
import cn.edu.zjut.mapper.ContractsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【contracts(租房合同表)】的数据库操作Service实现
* @createDate 2024-12-12 23:48:47
*/
@Service
public class ContractsServiceImpl extends ServiceImpl<ContractsMapper, Contracts>
    implements ContractsService{

}




