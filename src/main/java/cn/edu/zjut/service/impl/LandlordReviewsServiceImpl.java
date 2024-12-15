package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.LandlordReviews.LandlordReviews;
import cn.edu.zjut.service.LandlordReviewsService;
import cn.edu.zjut.mapper.LandlordReviewsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【landlord_reviews(房东对租客的评价表)】的数据库操作Service实现
* @createDate 2024-12-12 23:51:33
*/
@Service
public class LandlordReviewsServiceImpl extends ServiceImpl<LandlordReviewsMapper, LandlordReviews>
    implements LandlordReviewsService{

}




