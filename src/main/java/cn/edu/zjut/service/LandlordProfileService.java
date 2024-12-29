package cn.edu.zjut.service;

import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.LandlordProfile.req.*;
import cn.edu.zjut.entity.LandlordProfile.resp.LandlordProfileLoginResp;
import cn.edu.zjut.entity.TenantProfile.req.TenantIdcardReq;
import cn.edu.zjut.entity.admins.req.PwdChangeReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86173
* @description 针对表【landlord_profile(房东表)】的数据库操作Service
* @createDate 2024-12-18 23:19:39
*/
public interface LandlordProfileService extends IService<LandlordProfile> {
    LandlordProfile qureryByPhoneNum(String lAccount);
    void registerLandlord(LandlordProfileRegisterReq req);
    LandlordProfileLoginResp loginLandlord(LandlordProfileLoginReq req);
    LandlordProfile updateLandlordProfile(LandlordProfileUpdateReq req, String landlordId);
    void findPwd(PwdChangeReq req);
    void landlordIdCardCheck(LandlordIdcardReq req, String landlordId);
    LandlordListInfo getLandlordList(QueryLandlordReq req);
    // LandlordProfileService.java
    void modifyBalance(String landlordId, Double amount);

    //用房东的id来查看房东的信息
    public LandlordProfile getLandlordProfileById(String landlordId);

    public boolean updateLandlordProfile(LandlordProfile landlordProfile);

}
