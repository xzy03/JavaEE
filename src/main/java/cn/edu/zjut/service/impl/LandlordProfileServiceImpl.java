package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.mapper.LandlordProfileMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【landlord_profile(房东表)】的数据库操作Service实现
* @createDate 2024-12-12 23:50:53
*/
@Service
public class LandlordProfileServiceImpl extends ServiceImpl<LandlordProfileMapper, LandlordProfile>
    implements LandlordProfileService{

}




