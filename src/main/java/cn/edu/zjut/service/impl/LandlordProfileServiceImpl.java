package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.LandlordProfile.LandlordProfileConverter;
import cn.edu.zjut.entity.LandlordProfile.req.*;
import cn.edu.zjut.entity.LandlordProfile.resp.LandlordProfileLoginResp;
import cn.edu.zjut.entity.TenantProfile.resq.TenantListInfo;
import cn.edu.zjut.entity.admins.req.PwdChangeReq;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.utils.JwtUtil;
import cn.edu.zjut.utils.PasswordUtils;
import cn.edu.zjut.utils.UploadGiteeImgBed;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.mapper.LandlordProfileMapper;
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
    @Override
    public LandlordProfile updateLandlordProfile(LandlordProfileUpdateReq req, String landlordId) {
        LandlordProfile landlordProfile = landlordProfileService.getById(landlordId);
        landlordProfile.setLAccount(req.getLAccount());
        landlordProfile.setLPhoneNumber(req.getLPhoneNumber());
        landlordProfile.setLEmail(req.getLEmail());
        landlordProfile.setLProfilePicture(req.getLProfilePicture());
        landlordProfileService.updateById(landlordProfile);
        return landlordProfile;
    }
    @Override
    public void findPwd(PwdChangeReq req) {
        LandlordProfile landlordProfile = landlordProfileService.qureryByPhoneNum(req.getPhoneNum());
        if(landlordProfile == null) {
            throw new BusiException("用户不存在");
        }
        if (!req.getNewPassword().equals(req.getConfirmPassword())){
            throw new BusiException("两次输入密码不相同");
        }
        landlordProfile.setLPassword(PasswordUtils.encrypt(req.getNewPassword()));
        landlordProfileService.updateById(landlordProfile);
    }
    @Override
    public void landlordIdCardCheck(LandlordIdcardReq req, String landlordId) {
        LandlordProfile landlordProfile = landlordProfileService.getById(landlordId);
        if (landlordProfile == null) {
            throw new BusiException("用户不存在");
        }
        landlordProfile.setLCardNumber(req.getTCardNumber());
        landlordProfile.setLName(req.getTName());

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
            landlordProfile.setLCardImageFront(content.getStr("download_url")); // 设置正面图片下载地址
            uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(multipartFile1.getBytes());
            JSONResult = HttpUtil.post(targetURL1, uploadBodyMap);
            jsonObj = JSONUtil.parseObj(JSONResult);
            if (jsonObj == null || jsonObj.getObj("commit") == null) {
                throw new BusiException("上传失败");
            }
            content = JSONUtil.parseObj(jsonObj.getObj("content"));
            log.info("响应data为：" + content.getObj("download_url"));
            landlordProfile.setLCardImageBack(content.getStr("download_url")); // 设置背面图片下载地址
            landlordProfile.setLStatus("等待审核");
            landlordProfileService.updateById(landlordProfile);
        } catch (IOException e) {
            log.error("文件读取失败", e);
            throw new BusiException("文件读取失败，请稍后再试");
        }
    }
    @Override
    public LandlordListInfo getLandlordList(QueryLandlordReq req) {
        List<LandlordProfile> landlordList = baseMapper.getLandlordList(req);
        LandlordListInfo landlordListInfo = LandlordListInfo.builder()
                .landlordList(landlordList)
                .build();
        return landlordListInfo;
    }

    @Override
    public void modifyBalance(String landlordId, Double amount) {
        LandlordProfile landlord = this.getById(landlordId);
        if (landlord == null) {
            throw new BusiException("房东不存在");
        }
        landlord.setLBalance(landlord.getLBalance().add(BigDecimal.valueOf(amount)));
        this.updateById(landlord);
    }

}




