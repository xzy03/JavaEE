package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.Appointments.Appointments;
import cn.edu.zjut.entity.Appointments.req.AppointmentReq;
import cn.edu.zjut.entity.Appointments.req.AppointmentsConfirmReq;
import cn.edu.zjut.entity.Appointments.req.AppointmentsSubmitReq;
import cn.edu.zjut.entity.Appointments.req.AppointmentsUpdateReq;
import cn.edu.zjut.entity.admins.Admins;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.mapper.AppointmentsMapper;
import cn.edu.zjut.mapper.HouseMapper;
import cn.edu.zjut.service.AppointmentsService;
import cn.edu.zjut.service.HouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 预约服务实现类
 * @Author bc
 * @Date 2024/12/25
 */
@Service
public class AppointmentsServiceImpl extends ServiceImpl<AppointmentsMapper, Appointments>
        implements AppointmentsService {

    @Lazy
    @Resource
    HouseService houseService;

    @Override
    public void submitAppointment(AppointmentsSubmitReq req,String tenantId) {
        Appointments appointment = new Appointments(req,tenantId,houseService.getLandlordIdByHouseId(req.getHouseId()));
        this.save(appointment);
    }

    @Override
    public void updateAppointment(AppointmentsUpdateReq req) {
        Appointments appointment = this.getById(req.getAppointmentId());
        if (appointment == null) {
            throw new BusiException("预约不存在");
        }
        appointment.setHouseId(req.getHouseId());
        appointment.setAppointmentDate(req.getAppointmentDate());
        appointment.setStatus("待确认");
        this.updateById(appointment);
    }

    @Override
    public void cancelAppointment(String appointmentId) {
        this.removeById(appointmentId);
    }

    @Override
    public List<Appointments> viewTenantAppointments(String tenantId) {
        return this.lambdaQuery().eq(Appointments::getTenantId, tenantId).list();
    }

    @Override
    public List<Appointments> viewLandlordAppointments(String landlordId) {
        return this.lambdaQuery().eq(Appointments::getLandlordId, landlordId).list();
    }

    @Override
    public void confirmAppointment(AppointmentsConfirmReq req) {
        Appointments appointment = this.getById(req.getAppointmentId());
        if (appointment == null) {
            throw new BusiException("预约不存在");
        }
        appointment.setStatus(req.getStatus());
        this.updateById(appointment);
    }
    @Override
    public Appointments getAppointmentDetail(String appointmentId) {
        Appointments appointment = this.getById(appointmentId);
        if (appointment == null) {
            throw new BusiException("预约不存在");
        }
        return appointment;
    }
}
