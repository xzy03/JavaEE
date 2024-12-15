package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.RepairRequests.RepairRequests;
import cn.edu.zjut.service.RepairRequestsService;
import cn.edu.zjut.mapper.RepairRequestsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【repair_requests(报修管理表)】的数据库操作Service实现
* @createDate 2024-12-12 23:52:47
*/
@Service
public class RepairRequestsServiceImpl extends ServiceImpl<RepairRequestsMapper, RepairRequests>
    implements RepairRequestsService{

}




