package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.LandlordProfile.LandlordProfileConverter;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordProfileLoginReq;
import cn.edu.zjut.entity.LandlordProfile.req.LandlordProfileRegisterReq;
import cn.edu.zjut.entity.LandlordProfile.resp.LandlordProfileLoginResp;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.utils.JwtUtil;
import cn.edu.zjut.utils.PasswordUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.mapper.LandlordProfileMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
* @author 86173
* @description 针对表【landlord_profile(房东表)】的数据库操作Service实现
* @createDate 2024-12-18 23:19:39
*/
@Service
@Slf4j
public class LandlordProfileServiceImpl extends ServiceImpl<LandlordProfileMapper, LandlordProfile>
    implements LandlordProfileService{
    @Lazy
    @Resource
    LandlordProfileService landlordProfileService;
    @Override
    public void registerLandlord(LandlordProfileRegisterReq req) {
        if(landlordProfileService.qureryByPhoneNum(req.getLPhoneNumber()) != null) {
            throw new BusiException("手机号已存在");
        }
        LandlordProfile landlordProfile = LandlordProfile.builder()
                .lAccount(req.getLAccount())
                .lPassword(PasswordUtils.encrypt(req.getLPassword()))
                .lPhoneNumber(req.getLPhoneNumber())
                .lEmail(req.getLEmail())
                .lStatus("未认证")
                .lHouseStatus("未认证")
                .lBalance(BigDecimal.valueOf(0))
                .build();
        this.save(landlordProfile);
    }
    @Override
    public LandlordProfile qureryByPhoneNum(String lPhoneNumber) {
        return landlordProfileService.lambdaQuery().eq(LandlordProfile::getLPhoneNumber,lPhoneNumber).one();
    }
    @Override
    public LandlordProfileLoginResp loginLandlord(LandlordProfileLoginReq req) {
        LandlordProfile landlordProfile = landlordProfileService.qureryByPhoneNum(req.getLPhoneNumber());
        if(landlordProfile == null) {
            throw new BusiException("用户不存在");
        }
        if (PasswordUtils.verify(req.getLPassword(), landlordProfile.getLPassword())){
            LandlordProfileLoginResp landlordProfileLoginResp = LandlordProfileConverter.INSTANCE.toLandlordLoginResp(landlordProfile);
            log.info(landlordProfile.getLandlordId());
            log.info(landlordProfile.getLPhoneNumber());
            landlordProfileLoginResp.setToken(JwtUtil.generateToken(landlordProfile.getLandlordId(), landlordProfile.getLPhoneNumber()));
            return landlordProfileLoginResp;
        }
        throw new BusiException("密码错误");
    }
}




