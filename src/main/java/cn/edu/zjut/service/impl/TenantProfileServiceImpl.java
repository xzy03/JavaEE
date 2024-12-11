package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.service.TenantProfileService;
import cn.edu.zjut.mapper.TenantProfileMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【tenant_profile(大学生租客表)】的数据库操作Service实现
* @createDate 2024-12-11 14:28:24
*/
@Service
public class TenantProfileServiceImpl extends ServiceImpl<TenantProfileMapper, TenantProfile>
    implements TenantProfileService{

}




