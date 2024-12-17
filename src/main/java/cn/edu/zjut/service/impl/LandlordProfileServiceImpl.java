package cn.edu.zjut.service.impl;

import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.service.AdminsService;
import cn.edu.zjut.utils.PasswordUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.mapper.LandlordProfileMapper;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordRegisterReq;

/**
* @author 86173
* @description 针对表【landlord_profile(房东表)】的数据库操作Service实现
* @createDate 2024-12-12 23:50:53
*/
@Service
public class LandlordProfileServiceImpl extends ServiceImpl<LandlordProfileMapper, LandlordProfile>
    implements LandlordProfileService{
    @Lazy
    @Resource
    LandlordProfileService landlordProfileService;
    @Override
    public void registerLandlord(LandlordRegisterReq req) {
        if(landlordProfileService.qureryByPhoneNum(req.getLPhoneNumber()) != null) {
            throw new BusiException("手机号已存在");
        }
        LandlordProfile landlordProfile = LandlordProfile.builder()
            .lAccount(req.getLAccount())
            .lPassword(PasswordUtils.encrypt(req.getLPassword()))
            .lPhoneNumber(req.getLPhoneNumber())
            .lEmail(req.getLEmail())
            .build();
        save(landlordProfile);
    }
    @Override
    public LandlordProfile qureryByPhoneNum(String lPhoneNumber) {
        return landlordProfileService.lambdaQuery().eq(LandlordProfile::getLPhoneNumber,lPhoneNumber).one();
    }
}




