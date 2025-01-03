package cn.edu.zjut.service;

import cn.edu.zjut.entity.admins.Admins;
import cn.edu.zjut.entity.admins.req.*;
import cn.edu.zjut.entity.admins.resp.AdminsLoginResp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86173
* @description 针对表【admins(管理员表)】的数据库操作Service
* @createDate 2024-12-11 12:46:29
*/
public interface AdminsService extends IService<Admins> {
    Admins qureryByUsername(String adUsername);
    Admins qureryByPhoneNum(String adPhoneNum);
    Admins qureryByEmail(String adEmail);
    void registerAdmin(AdminsRegisterReq req);
    AdminsLoginResp login(AdminsLoginReq req);
    void changeUserInfo(AdminsInfoReq req, String userId);
    void findPwd(PwdChangeReq req);
    void idCardCheck(CheckReq req);
    void studentCardCheck(CheckReq req);
    void landlordIdCardCheck(CheckReq req);
    void landlordHouseCardCheck(CheckReq req);
}
