package cn.edu.zjut.service;

import cn.edu.zjut.entity.Appointments.Appointments;
import cn.edu.zjut.entity.Appointments.req.AppointmentReq;
import cn.edu.zjut.entity.Appointments.req.AppointmentsConfirmReq;
import cn.edu.zjut.entity.Appointments.req.AppointmentsSubmitReq;
import cn.edu.zjut.entity.Appointments.req.AppointmentsUpdateReq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86173
* @description 针对表【appointments(预约看房记录表)】的数据库操作Service
* @createDate 2024-12-12 23:48:09W
*/
public interface AppointmentsService extends IService<Appointments> {
    void submitAppointment(AppointmentsSubmitReq req,String tenantId);
    void updateAppointment(AppointmentsUpdateReq req);
    void cancelAppointment(String appointmentId);
    List<Appointments> viewTenantAppointments(String tenantId);
    List<Appointments> viewLandlordAppointments(String tenantId);
    void confirmAppointment(AppointmentsConfirmReq req);
    Appointments getAppointmentDetail(String appointmentId);
}
