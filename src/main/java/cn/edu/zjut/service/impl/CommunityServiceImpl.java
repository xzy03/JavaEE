package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.Community.Community;
import cn.edu.zjut.service.CommunityService;
import cn.edu.zjut.mapper.CommunityMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【community(小区信息表)】的数据库操作Service实现
* @createDate 2024-12-12 23:48:27
*/
@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community>
    implements CommunityService{

}




