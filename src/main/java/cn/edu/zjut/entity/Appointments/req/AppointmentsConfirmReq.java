package cn.edu.zjut.entity.Appointments.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 预约请求对象
 * @Author bc
 * @Date 2024/12/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentsConfirmReq {

    @Schema(description = "预约ID")
    private String appointmentId;

    @Schema(description = "预约状态")
    @NotBlank
    private String status;
}