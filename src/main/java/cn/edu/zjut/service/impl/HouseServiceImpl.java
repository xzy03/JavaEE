package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.House.House;
import cn.edu.zjut.service.HouseService;
import cn.edu.zjut.mapper.HouseMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【house(房源信息表)】的数据库操作Service实现
* @createDate 2024-12-12 23:50:23
*/
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House>
    implements HouseService{

}




