package cn.edu.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.zjut.entity.Appointments.Appointments;
import cn.edu.zjut.service.AppointmentsService;
import cn.edu.zjut.mapper.AppointmentsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【appointments(预约看房记录表)】的数据库操作Service实现
* @createDate 2024-12-12 23:48:09
*/
@Service
public class AppointmentsServiceImpl extends ServiceImpl<AppointmentsMapper, Appointments>
    implements AppointmentsService{

}




