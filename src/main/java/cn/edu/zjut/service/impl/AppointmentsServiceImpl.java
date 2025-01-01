package cn.edu.zjut.service.impl;

import cn.edu.zjut.entity.Appointments.Appointments;
import cn.edu.zjut.entity.Appointments.req.AppointmentReq;
import cn.edu.zjut.entity.Appointments.req.AppointmentsConfirmReq;
import cn.edu.zjut.entity.Appointments.req.AppointmentsSubmitReq;
import cn.edu.zjut.entity.Appointments.req.AppointmentsUpdateReq;
import cn.edu.zjut.entity.Appointments.resp.*;
import cn.edu.zjut.entity.admins.Admins;
import cn.edu.zjut.exception.apiException.BusiException;
import cn.edu.zjut.mapper.AppointmentsMapper;
import cn.edu.zjut.mapper.HouseMapper;
import cn.edu.zjut.service.AppointmentsService;
import cn.edu.zjut.service.HouseService;
import cn.edu.zjut.service.LandlordProfileService;
import cn.edu.zjut.service.TenantProfileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

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

    @Lazy
    @Resource
    LandlordProfileService landlordProfileService;

    @Lazy
    @Resource
    TenantProfileService tenantProfileService;


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
        appointment.setAppointmentDate(req.getAppointmentDate());
        appointment.setStatus("待确认");
        this.updateById(appointment);
    }

    @Override
    public void cancelAppointment(String appointmentId) {
        this.removeById(appointmentId);
    }

    @Override
    public List<AppointmentViewTenantResp> viewTenantAppointments(String tenantId) {
        // 查询该租户的所有预约记录
        List<Appointments> appointments = this.lambdaQuery()
                .eq(Appointments::getTenantId, tenantId)
                .list();

        // 将预约记录转换为响应对象列表
        return appointments.stream().map(appointment -> {
            String hTitle = houseService.getHTitleByHouseId(appointment.getHouseId());
            String lName = landlordProfileService.getLNameByLandlordId(appointment.getLandlordId());

            return AppointmentViewTenantResp.builder()
                    .appointmentId(appointment.getAppointmentId())
                    .hTitle(hTitle)
                    .lName(lName)
                    .appointmentDate(appointment.getAppointmentDate())
                    .status(appointment.getStatus())
                    .build();
        }).collect(Collectors.toList());
    }


    @Override
    public List<AppointmentViewLandlordResp> viewLandlordAppointments(String landlordId) {
        // 查询该房东的所有预约记录
        List<Appointments> appointments = this.lambdaQuery()
                .eq(Appointments::getLandlordId, landlordId)
                .list();

        // 将预约记录转换为响应对象列表
        return appointments.stream().map(appointment -> {
            String hTitle = houseService.getHTitleByHouseId(appointment.getHouseId());
            String tName = tenantProfileService.getTNameByTenantId(appointment.getTenantId());

            return AppointmentViewLandlordResp.builder()
                    .appointmentId(appointment.getAppointmentId())
                    .hTitle(hTitle)
                    .tName(tName)
                    .appointmentDate(appointment.getAppointmentDate())
                    .status(appointment.getStatus())
                    .build();
        }).collect(Collectors.toList());
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
