package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.Posts.Posts;
import cn.edu.zjut.service.PostsService;
import cn.edu.zjut.mapper.PostsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【posts(帖子表)】的数据库操作Service实现
* @createDate 2024-12-12 23:51:56
*/
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts>
    implements PostsService{

}




