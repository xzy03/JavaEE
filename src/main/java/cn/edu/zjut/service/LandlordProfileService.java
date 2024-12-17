package cn.edu.zjut.service;

import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordProfileLoginReq;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordProfileRegisterReq;
import cn.edu.zjut.entity.LandlordProfile.resp.LandlordProfileLoginResp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86173
* @description 针对表【landlord_profile(房东表)】的数据库操作Service
* @createDate 2024-12-12 23:50:53
*/
public interface LandlordProfileService extends IService<LandlordProfile> {
    LandlordProfile qureryByPhoneNum(String lAccount);
    void registerLandlord(LandlordProfileRegisterReq req);
    LandlordProfileLoginResp loginLandlord(LandlordProfileLoginReq req);
}
