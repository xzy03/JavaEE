package cn.edu.zjut.service;

import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.entity.TenantProfile.req.*;
import cn.edu.zjut.entity.TenantProfile.resq.TenantListInfo;
import cn.edu.zjut.entity.TenantProfile.resq.TenantLoginResp;
import cn.edu.zjut.entity.admins.req.PwdChangeReq;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 86173
* @description 针对表【tenant_profile(大学生租客表)】的数据库操作Service
* @createDate 2024-12-25 13:41:08
*/
public interface TenantProfileService extends IService<TenantProfile> {
    void registerTenant(TenantRegisterReq req);
    TenantProfile qureryByPhoneNum(String tPhoneNumber);
    TenantLoginResp loginTenant(TenantLoginReq req);
    TenantProfile updateTenateProfile(TenantUpdateReq req, String tenantId);
    void findPwd(PwdChangeReq req);
    void idCardCheck(TenantIdcardReq req, String tenantId);
    void studentInfo(String tUniversity, String tMajor, MultipartFile tProfilePicture, String tenantId);
    TenantListInfo getTenantList(QueryTenantReq req);
    // TenantProfileService.java
    void modifyBalance(String tenantId, Double amount);
    TenantListInfo getTenantListByHouseId(String houseId);
    public String getTNameByTenantId(String tenantId);
}
