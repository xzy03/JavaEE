package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.LandlordProperties.LandlordProperties;
import cn.edu.zjut.service.LandlordPropertiesService;
import cn.edu.zjut.mapper.LandlordPropertiesMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【landlord_properties(房东租赁房屋表)】的数据库操作Service实现
* @createDate 2024-12-12 23:51:10
*/
@Service
public class LandlordPropertiesServiceImpl extends ServiceImpl<LandlordPropertiesMapper, LandlordProperties>
    implements LandlordPropertiesService{

}




