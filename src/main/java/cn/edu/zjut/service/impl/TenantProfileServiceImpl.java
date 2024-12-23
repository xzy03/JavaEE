package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.TenantProfile.TenantConverter;
import cn.edu.zjut.entity.TenantProfile.req.TenantIdcardReq;
import cn.edu.zjut.entity.TenantProfile.req.TenantLoginReq;
import cn.edu.zjut.entity.TenantProfile.req.TenantRegisterReq;
import cn.edu.zjut.entity.TenantProfile.req.TenantUpdateReq;
import cn.edu.zjut.entity.TenantProfile.resq.TenantLoginResp;
import cn.edu.zjut.entity.admins.req.PwdChangeReq;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.utils.JwtUtil;
import cn.edu.zjut.utils.PasswordUtils;
import cn.edu.zjut.utils.UploadGiteeImgBed;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import cn.edu.zjut.service.TenantProfileService;
import cn.edu.zjut.mapper.TenantProfileMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
* @author 86173
* @description 针对表【tenant_profile(大学生租客表)】的数据库操作Service实现
* @createDate 2024-12-17 18:50:44
*/
@Service
@Slf4j
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
            .tIdentityStatus("未认证")
            .tStatus("未审核")
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

    @Override
    public TenantProfile updateTenateProfile(TenantUpdateReq req, String tenantId) {
        TenantProfile tenantProfile = tenantProfileService.getById(tenantId);
        tenantProfile.setTAccount(req.getTAccount());
        tenantProfile.setTPhoneNumber(req.getTPhoneNumber());
        tenantProfile.setTEmail(req.getTEmail());
        tenantProfile.setTProfilePicture(req.getTProfilePicture());
        this.updateById(tenantProfile);
        return tenantProfile;
    }
    @Override
    public void findPwd(PwdChangeReq req) {
        TenantProfile tenantProfile = tenantProfileService.qureryByPhoneNum(req.getPhoneNum());
        if(tenantProfile == null) {
            throw new BusiException("用户不存在");
        }
        if (!req.getNewPassword().equals(req.getConfirmPassword())){
            throw new BusiException("两次输入密码不相同");
        }
        tenantProfile.setTPassword(PasswordUtils.encrypt(req.getNewPassword()));
        tenantProfileService.updateById(tenantProfile);
    }
    @Override
    public void idCardCheck(TenantIdcardReq req, String tenantId) {
        TenantProfile tenantProfile = tenantProfileService.getById(tenantId);
        if (tenantProfile == null) {
            throw new BusiException("用户不存在");
        }
        tenantProfile.setTCardNumber(req.getTCardNumber());
        tenantProfile.setTName(req.getTName());

        MultipartFile multipartFile = req.getTCardImageFront();
        MultipartFile multipartFile1 = req.getTCardImageBack();
        String originalFilename = multipartFile.getOriginalFilename();
        String originalFilename1 = multipartFile1.getOriginalFilename();
        if (originalFilename == null || originalFilename1 == null) {
            throw new BusiException("文件名为空");
        }
        String targetURL = UploadGiteeImgBed.createUploadFileUrl(originalFilename);
        String targetURL1 = UploadGiteeImgBed.createUploadFileUrl(originalFilename1);
        log.info("目标url：" + targetURL);
        log.info("目标url：" + targetURL1);
        try {
            Map<String, Object> uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(multipartFile.getBytes());
            String JSONResult = HttpUtil.post(targetURL, uploadBodyMap);
            JSONObject jsonObj = JSONUtil.parseObj(JSONResult);
            if (jsonObj == null || jsonObj.getObj("commit") == null) {
                throw new BusiException("上传失败");
            }
            JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));
            log.info("响应data为：" + content.getObj("download_url"));
            tenantProfile.setTCardImageFront(content.getStr("download_url")); // 设置正面图片下载地址
            uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(multipartFile1.getBytes());
            JSONResult = HttpUtil.post(targetURL1, uploadBodyMap);
            jsonObj = JSONUtil.parseObj(JSONResult);
            if (jsonObj == null || jsonObj.getObj("commit") == null) {
                throw new BusiException("上传失败");
            }
            content = JSONUtil.parseObj(jsonObj.getObj("content"));
            log.info("响应data为：" + content.getObj("download_url"));
            tenantProfile.setTCatImageBack(content.getStr("download_url")); // 设置背面图片下载地址
            tenantProfile.setTIdentityStatus("等待审核");
            tenantProfileService.updateById(tenantProfile);
        } catch (IOException e) {
            log.error("文件读取失败", e);
            throw new BusiException("文件读取失败，请稍后再试");
        }
    }
    @Override
    public void studentInfo(String tUniversity, String tMajor, MultipartFile tProfilePicture, String tenantId) {
        TenantProfile tenantProfile = tenantProfileService.getById(tenantId);
        if (tenantProfile == null) {
            throw new BusiException("用户不存在");
        }
        tenantProfile.setTUniversity(tUniversity);
        tenantProfile.setTMajor(tMajor);
        String originalFilename = tProfilePicture.getOriginalFilename();
        if (originalFilename == null) {
            throw new BusiException("文件名为空");
        }
        String targetURL = UploadGiteeImgBed.createUploadFileUrl(originalFilename);
        log.info("目标url：" + targetURL);
        try {
            Map<String, Object> uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(tProfilePicture.getBytes());
            String JSONResult = HttpUtil.post(targetURL, uploadBodyMap);
            JSONObject jsonObj = JSONUtil.parseObj(JSONResult);
            if (jsonObj == null || jsonObj.getObj("commit") == null) {
                throw new BusiException("上传失败");
            }
            JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));
            log.info("响应data为：" + content.getObj("download_url"));
            tenantProfile.setTProfilePicture(content.getStr("download_url")); // 设置图片下载地址
            tenantProfileService.updateById(tenantProfile);
        } catch (IOException e) {
            log.error("文件读取失败", e);
            throw new BusiException("文件读取失败，请稍后再试");
        }
    }

}




