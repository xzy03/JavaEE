package cn.edu.zjut.service;

import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.entity.TenantProfile.req.TenantLoginReq;
import cn.edu.zjut.entity.TenantProfile.req.TenantRegisterReq;
import cn.edu.zjut.entity.TenantProfile.req.TenantUpdateReq;
import cn.edu.zjut.entity.TenantProfile.resq.TenantLoginResp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86173
* @description 针对表【tenant_profile(大学生租客表)】的数据库操作Service
* @createDate 2024-12-17 18:50:44
*/
public interface TenantProfileService extends IService<TenantProfile> {
    void registerTenant(TenantRegisterReq req);
    TenantProfile qureryByPhoneNum(String tPhoneNumber);
    TenantLoginResp loginTenant(TenantLoginReq req);
    TenantProfile updateTenateProfile(TenantUpdateReq req, String tenantId);
}
