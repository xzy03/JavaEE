package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.TenantLandlordReviews.TenantLandlordReviews;
import cn.edu.zjut.service.TenantLandlordReviewsService;
import cn.edu.zjut.mapper.TenantLandlordReviewsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【tenant_landlord_reviews(房东对租客的评价表)】的数据库操作Service实现
* @createDate 2024-12-12 23:53:33
*/
@Service
public class TenantLandlordReviewsServiceImpl extends ServiceImpl<TenantLandlordReviewsMapper, TenantLandlordReviews>
    implements TenantLandlordReviewsService{

}




