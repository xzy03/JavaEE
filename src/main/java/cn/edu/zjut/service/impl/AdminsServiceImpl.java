package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.House.House;
import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.entity.admins.AdminsConverter;
import cn.edu.zjut.entity.admins.req.*;
import cn.edu.zjut.entity.admins.resp.AdminsLoginResp;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.service.HouseService;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.service.TenantProfileService;
import cn.edu.zjut.utils.PasswordUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.admins.Admins;
import cn.edu.zjut.service.AdminsService;
import cn.edu.zjut.mapper.AdminsMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import cn.edu.zjut.utils.JwtUtil;

/**
* @author 86173
* @description 针对表【admins(管理员表)】的数据库操作Service实现
* @createDate 2024-12-11 12:46:29
*/
@Service
@Slf4j
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins>
    implements AdminsService{
    @Lazy
    @Resource
    AdminsService adminsService;
    @Lazy
    @Resource
    TenantProfileService tenantProfileService;
    @Lazy
    @Resource
    LandlordProfileService landlordProfileService;
    @Lazy
    @Resource
    HouseService houseService;
    @Override
    public Admins qureryByUsername(String adUsername) {
        return adminsService.lambdaQuery().eq(Admins::getAdUsername, adUsername).one();
    }
    @Override
    public Admins qureryByPhoneNum(String adPhoneNum) {
        return adminsService.lambdaQuery().eq(Admins::getAdPhone, adPhoneNum).one();
    }
    @Override
    public Admins qureryByEmail(String adEmail) {
        return adminsService.lambdaQuery().eq(Admins::getAdEmail, adEmail).one();
    }
    @Override
    public void registerAdmin(AdminsRegisterReq req) {
        if(adminsService.qureryByPhoneNum(req.getAdPhone()) != null) {
            throw new BusiException("手机号已存在");
        }
        if(adminsService.qureryByEmail(req.getAdEmail()) != null) {
            throw new BusiException("邮箱已存在");
        }
        if(adminsService.qureryByUsername(req.getAdUsername()) != null) {
            throw new BusiException("用户名已存在");
        }
        Admins admins = new Admins(req);
        admins.setAdPasswordHash(PasswordUtils.encrypt(req.getAdPasswordHash()));
        this.save(admins);
    }
    @Override
    public AdminsLoginResp login(AdminsLoginReq req) {
        Admins admins = adminsService.qureryByPhoneNum(req.getAdPhone());
        if (admins == null){
            throw new BusiException("手机号不存在");
        }
        if (PasswordUtils.verify(req.getAdPasswordHash(), admins.getAdPasswordHash())){
            AdminsLoginResp loginResp = AdminsConverter.INSTANCE.toLoginResp(admins);
            log.info(admins.getAdminId());
            log.info(admins.getAdPhone());
            loginResp.setToken(JwtUtil.generateToken(admins.getAdminId(), admins.getAdPhone()));;
            return loginResp;
        }
        throw new BusiException("密码错误");
    }
    @Override
    public void changeUserInfo(AdminsInfoReq req, String userId) {
        Admins admins = adminsService.getById(userId);
        if (adminsService.qureryByPhoneNum(req.getAdPhone()) != null){
            throw new BusiException("手机号存在");
        }
        if(adminsService.qureryByUsername(req.getAdUsername()) != null){
            throw new BusiException("用户名存在");
        }
        admins.setAdUsername(req.getAdUsername());
        admins.setAdEmail(req.getAdEmail());
        admins.setAdPhone(req.getAdPhone());
        adminsService.updateById(admins);
    }
    @Override
    public void findPwd(PwdChangeReq req) {
        Admins admins = adminsService.qureryByPhoneNum(req.getPhoneNum());
        if (admins == null){
            throw new BusiException("手机号不存在");
        }
        if (!req.getNewPassword().equals(req.getConfirmPassword())){
            throw new BusiException("两次输入密码不相同");
        }
        admins.setAdPasswordHash(PasswordUtils.encrypt(req.getNewPassword()));
        adminsService.updateById(admins);
    }
    @Override
    public void idCardCheck(CheckReq req) {
        TenantProfile tenantProfile = tenantProfileService.getById(req.getId());
        if (tenantProfile == null){
            throw new BusiException("租客不存在");
        }
        tenantProfile.setTIdentityStatus(req.getContent());
        tenantProfileService.updateById(tenantProfile);
    }
    @Override
    public void studentCardCheck(CheckReq req) {
        TenantProfile tenantProfile = tenantProfileService.getById(req.getId());
        if (tenantProfile == null){
            throw new BusiException("租客不存在");
        }
        tenantProfile.setTStatus(req.getContent());
        tenantProfileService.updateById(tenantProfile);
    }
    @Override
    public void landlordIdCardCheck(CheckReq req){
        LandlordProfile landlordProfile = landlordProfileService.getById(req.getId());
        if (landlordProfile == null){
            throw new BusiException("房东不存在");
        }
        landlordProfile.setLStatus(req.getContent());
        landlordProfileService.updateById(landlordProfile);
    }
    @Override
    public void landlordHouseCardCheck(CheckReq req){
        House house = houseService.getById(req.getId());
//        LandlordProfile landlordProfile = landlordProfileService.getById(house.getLandlordId());
        if (house == null){
            throw new BusiException("房屋不存在");
        }
        house.setLHouseLicenseState(req.getContent());
//        if(landlordProfile.getLHouseStatus()==null || !landlordProfile.getLHouseStatus().equals("已认证")){
//            landlordProfile.setLHouseStatus(req.getContent());
//            landlordProfileService.updateById(landlordProfile);
//        }
        houseService.updateById(house);
    }
}




