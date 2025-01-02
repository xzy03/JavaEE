package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.entity.TenantProfile.TenantConverter;
import cn.edu.zjut.entity.TenantProfile.req.*;
import cn.edu.zjut.entity.TenantProfile.resq.TenantListInfo;
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
import java.util.List;
import java.util.Map;

/**
* @author 86173
* @description 针对表【tenant_profile(大学生租客表)】的数据库操作Service实现
* @createDate 2024-12-25 13:41:08
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
        if(tenantProfileService.qureryByEmail(req.getTEmail()) != null) {
            throw new BusiException("邮箱已存在");
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
    public TenantProfile qureryByEmail(String tEmail) {
        return tenantProfileService.lambdaQuery().eq(TenantProfile::getTEmail,tEmail).one();
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
        if (tenantProfileService.qureryByPhoneNum(req.getTPhoneNumber()) != null) {
            throw new BusiException("手机号已存在");
        }
        if (tenantProfileService.qureryByEmail(req.getTEmail()) != null) {
            throw new BusiException("邮箱已存在");
        }
        TenantProfile tenantProfile = tenantProfileService.getById(tenantId);
        tenantProfile.setTAccount(req.getTAccount());
        tenantProfile.setTPhoneNumber(req.getTPhoneNumber());
        tenantProfile.setTEmail(req.getTEmail());
        tenantProfile.setTProfilePicture(req.getTProfilePicture());
        tenantProfile.setTIntroduction(req.getTIntroduction());
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
        if(tenantProfile.getTIdentityStatus()!=null){
            if(tenantProfile.getTIdentityStatus().equals("已审核")) {
                throw new BusiException("已审核，无需重复提交");
            }
            else if(tenantProfile.getTIdentityStatus().equals("等待审核")) {
                throw new BusiException("正在审核中，请耐心等待");
            }
        }
        tenantProfile.setTCardNumber(req.getTCardNumber());
        tenantProfile.setTName(req.getTName());
        tenantProfile.setTSex(req.getTSex());
        tenantProfile.setTBirth(req.getTBirth());

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
            tenantProfile.setTCardImageBack(content.getStr("download_url")); // 设置背面图片下载地址
            tenantProfile.setTIdentityStatus("等待审核");//身份证审核状态
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
        if(tenantProfile.getTStatus()!=null){
            if(tenantProfile.getTStatus().equals("已审核")){
                throw new BusiException("已审核，无需重复提交");
            }
            else if(tenantProfile.getTStatus().equals("等待审核")){
                throw new BusiException("正在审核中，请耐心等待");
            }
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
            tenantProfile.setTStatus("等待审核");//学生证的审核状态
            tenantProfileService.updateById(tenantProfile);
        } catch (IOException e) {
            log.error("文件读取失败", e);
            throw new BusiException("文件读取失败，请稍后再试");
        }
    }
    @Override
    public TenantListInfo getTenantList(QueryTenantReq req) {
        List<TenantProfile> tenantList = baseMapper.getTenantList(req);
        TenantListInfo tenantListInfo = TenantListInfo.builder()
                .tenantList(tenantList)
                .build();
        return tenantListInfo;
    }

    @Override
    public void modifyBalance(String tenantId, Double amount) {
        TenantProfile tenant = this.getById(tenantId);
        if (tenant == null) {
            throw new BusiException("租户不存在");
        }
        tenant.setTBalance(tenant.getTBalance().add(BigDecimal.valueOf(amount)));
        this.updateById(tenant);
    }
    @Override
    public TenantListInfo getTenantListByHouseId(String houseId) {
        List<TenantProfile> tenantList = baseMapper.getTenantListByHouseId(houseId);
        TenantListInfo tenantListInfo = TenantListInfo.builder()
                .tenantList(tenantList)
                .build();
        return tenantListInfo;
    }

    @Override
    public String getTNameByTenantId(String tenantId) {
        TenantProfile tenantProfile = baseMapper.selectById(tenantId);
        if (tenantProfile == null) {
            throw new BusiException("租户不存在");
        }
        return tenantProfile.getTName();
    }

}




