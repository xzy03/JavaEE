package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.admins.AdminsConverter;
import cn.edu.zjut.entity.admins.req.AdminsLoginReq;
import cn.edu.zjut.entity.admins.req.AdminsRegisterReq;
import cn.edu.zjut.entity.admins.resp.AdminsLoginResp;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.exception.apiException.BusiException;
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
    @Override
    public Admins qureryByUsername(String adUsername) {
        return adminsService.lambdaQuery().eq(Admins::getAdUsername, adUsername).one();

    }
    @Override
    public void registerAdmin(AdminsRegisterReq req) {
        if(adminsService.qureryByUsername(req.getAdUsername()) != null) {
            throw new BusiException("用户名已存在");
        }
        Admins admins = new Admins(req);
        admins.setAdPasswordHash(PasswordUtils.encrypt(req.getAdPasswordHash()));
        this.save(admins);
    }
    @Override
    public AdminsLoginResp login(AdminsLoginReq req) {
        Admins admins = adminsService.qureryByUsername(req.getAdUsername());
        if (PasswordUtils.verify(req.getAdPasswordHash(), admins.getAdPasswordHash())){
            AdminsLoginResp loginResp = AdminsConverter.INSTANCE.toLoginResp(admins);
            loginResp.setToken(JwtUtil.generateToken(admins.getAdminId(), admins.getAdUsername()));
            return loginResp;
        }
        throw new BusiException("密码错误");
    }

}




