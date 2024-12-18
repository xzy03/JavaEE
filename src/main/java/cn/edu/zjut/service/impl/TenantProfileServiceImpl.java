package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.TenantProfile.TenantConverter;
import cn.edu.zjut.entity.TenantProfile.req.TenantLoginReq;
import cn.edu.zjut.entity.TenantProfile.req.TenantRegisterReq;
import cn.edu.zjut.entity.TenantProfile.resq.TenantLoginResp;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.utils.JwtUtil;
import cn.edu.zjut.utils.PasswordUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.service.TenantProfileService;
import cn.edu.zjut.mapper.TenantProfileMapper;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
* @author 86173
* @description 针对表【tenant_profile(大学生租客表)】的数据库操作Service实现
* @createDate 2024-12-17 18:50:44
*/
@Service
public class TenantProfileServiceImpl extends ServiceImpl<TenantProfileMapper, TenantProfile>
    implements TenantProfileService{
    @Lazy
    @Resource
    TenantProfileService tenantProfileService;
    @Override
    public void registerTenant(TenantRegisterReq req) {
        if(tenantProfileService.qureryByPhoneNum(req.getTPhoneNumber()) != null) {
            throw new BusiException("手机号已存在");
        }
        TenantProfile tenantProfile = TenantProfile.builder()
            .tAccount(req.getTAccount())
            .tPassword(PasswordUtils.encrypt(req.getTPassword()))
            .tPhoneNumber(req.getTPhoneNumber())
            .tEmail(req.getTEmail())
            .tBalance(BigDecimal.valueOf(0))
            .build();
        this.save(tenantProfile);
    }
    @Override
    public TenantProfile qureryByPhoneNum(String tPhoneNumber) {
        return tenantProfileService.lambdaQuery().eq(TenantProfile::getTPhoneNumber,tPhoneNumber).one();
    }

    @Override
    public TenantLoginResp loginTenant(TenantLoginReq req) {
        TenantProfile tenantProfile = tenantProfileService.qureryByPhoneNum(req.getPhoneNum());
        if (tenantProfile == null){
            throw new BusiException("手机号不存在");
        }
        if (PasswordUtils.verify(req.getPassword(), tenantProfile.getTPassword())){
            TenantLoginResp tenantLoginResp = TenantConverter.INSTANCE.toLoginResp(tenantProfile);
            tenantLoginResp.setToken(JwtUtil.generateToken(tenantProfile.getTenantId(), tenantProfile.getTPhoneNumber()));
            return tenantLoginResp;
        }
        throw new BusiException("密码错误");
    }
}




