package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.HouseTenants.HouseTenants;
import cn.edu.zjut.service.HouseTenantsService;
import cn.edu.zjut.mapper.HouseTenantsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【house_tenants(房屋租户关系表)】的数据库操作Service实现
* @createDate 2024-12-12 23:50:35
*/
@Service
public class HouseTenantsServiceImpl extends ServiceImpl<HouseTenantsMapper, HouseTenants>
    implements HouseTenantsService{

}




