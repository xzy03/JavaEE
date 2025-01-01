package cn.edu.zjut.controller;

import cn.edu.zjut.entity.Appointments.Appointments;
import cn.edu.zjut.entity.Appointments.req.*;
import cn.edu.zjut.entity.Appointments.resp.*;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.service.AppointmentsService;
import cn.edu.zjut.utils.UserInfoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 预约控制器
 * @Author bc
 * @Date 2024/12/25
 */
@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
@Tag(name = "预约管理", description = "预约相关的 API")
public class AppointmentsController {

    private final AppointmentsService appointmentService;

    @Operation(summary = "提交预约")
    @PostMapping("/submit")
    public CommonResult<Void> submitAppointment(@Validated @RequestBody AppointmentsSubmitReq req) {
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        appointmentService.submitAppointment(req,userTokenInfoDto.getUserId());
        return CommonResult.success(null);
    }

    @Operation(summary = "更改预约")
    @PostMapping("/update")
    public CommonResult<Void> updateAppointment(@Validated @RequestBody AppointmentsUpdateReq req) {
        appointmentService.updateAppointment(req);
        return CommonResult.success(null);
    }

    @Operation(summary = "取消预约")
    @PostMapping("/cancel")
    public CommonResult<Void> cancelAppointment(@Validated @RequestBody AppointmentsIdReq req) {
        appointmentService.cancelAppointment(req.getAppointmentId());
        return CommonResult.success(null);
    }

    @Operation(summary = "大学生租户查看预约")
    @PostMapping("/view/tenant")
    public CommonResult<List<AppointmentViewTenantResp>> viewTenantAppointments() {
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        List<AppointmentViewTenantResp> appointments = appointmentService.viewTenantAppointments(userTokenInfoDto.getUserId());
        return CommonResult.success(appointments);
    }

    @Operation(summary = "房东查看预约")
    @PostMapping("/view/landlord")
    public CommonResult<List<AppointmentViewLandlordResp>> viewLandlordAppointments() {
        UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
        List<AppointmentViewLandlordResp> appointments = appointmentService.viewLandlordAppointments(userTokenInfoDto.getUserId());
        return CommonResult.success(appointments);
    }

    @Operation(summary = "查看预约详情")
    @PostMapping("/detail")
    public CommonResult<Appointments> getAppointmentDetail(@Validated @RequestBody AppointmentsIdReq req) {
        Appointments appointment = appointmentService.getAppointmentDetail(req.getAppointmentId());
        return CommonResult.success(appointment);
    }


    @Operation(summary = "确认预约")
    @PostMapping("/confirm")
    public CommonResult<Void> confirmAppointment(@Validated @RequestBody AppointmentsConfirmReq req) {
        appointmentService.confirmAppointment(req);
        return CommonResult.success(null);
    }

}
